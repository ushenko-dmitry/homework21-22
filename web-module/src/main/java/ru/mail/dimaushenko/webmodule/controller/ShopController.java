package ru.mail.dimaushenko.webmodule.controller;

import java.lang.invoke.MethodHandles;
import java.util.List;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mail.dimaushenko.service.ShopService;
import ru.mail.dimaushenko.service.model.ShopDTO;

@Controller
@RequestMapping("/shops")
public class ShopController {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping()
    public String getShops(Model model) {
        List<ShopDTO> shops = shopService.getShops();
        model.addAttribute("shops", shops);
        return "shops";
    }

    @GetMapping("/add")
    public String addShop(Model model) {
        model.addAttribute("new_shop", new ShopDTO());
        return "add_shop";
    }

    @PostMapping("/add")
    public String addShop(
            @Valid @ModelAttribute(name = "new_shop") ShopDTO shopDTO,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            logErrors(allErrors);
            return "add_shop";
        }
        shopService.addShop(shopDTO);
        return "redirect:/shops";
    }

    private void logErrors(List<ObjectError> allErrors) {
        for (ObjectError error : allErrors) {
            logger.error("Error with add shop: " + error.getObjectName() + " - " + error.getDefaultMessage());
        }
    }

}
