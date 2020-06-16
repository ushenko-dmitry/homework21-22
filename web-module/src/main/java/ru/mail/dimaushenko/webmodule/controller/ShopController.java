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
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.mail.dimaushenko.service.ShopService;
import ru.mail.dimaushenko.service.model.PaginationDTO;
import ru.mail.dimaushenko.service.model.ShopDTO;
import ru.mail.dimaushenko.service.model.ShopsFilterDTO;
import ru.mail.dimaushenko.service.model.ViewShopsDTO;
import static ru.mail.dimaushenko.webmodule.constants.FilterConstants.DEFAULT_FILTER_SHOPS_LOCATION;
import static ru.mail.dimaushenko.webmodule.constants.PaginationConstants.DEFAULT_CURRENT_PAGE;
import static ru.mail.dimaushenko.webmodule.constants.PaginationConstants.DEFAULT_ITEMS_PER_PAGE;

@Controller
@RequestMapping("/shops")
@SessionAttributes(types = ViewShopsDTO.class)
public class ShopController {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping()
    public String getShops(
            ViewShopsDTO viewShopsDTO,
            Model model
    ) {
        PaginationDTO pagination = setPagination(viewShopsDTO.getPagination());
        viewShopsDTO.setPagination(pagination);
        ShopsFilterDTO filter = setFilter(viewShopsDTO.getFilter());
        viewShopsDTO.setFilter(filter);
        List<ShopDTO> shops = shopService.getShops(pagination, filter);
        viewShopsDTO.setShops(shops);

        model.addAttribute("viewShops", viewShopsDTO);
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

    private PaginationDTO setPagination(PaginationDTO pagination) {
        if (pagination == null) {
            pagination = new PaginationDTO();
        }
        if (pagination.getCurrentPage() == null) {
            pagination.setCurrentPage(DEFAULT_CURRENT_PAGE);
        }
        if (pagination.getElementsPerPage() == null) {
            pagination.setElementsPerPage(DEFAULT_ITEMS_PER_PAGE);
        }
        Integer amountShops = shopService.getAmountShops();
        Integer amountPages = amountShops / pagination.getElementsPerPage();
        if (amountShops % pagination.getElementsPerPage() > 0) {
            amountPages++;
        }
        if (amountPages == 0) {
            amountPages++;
        }
        pagination.setAmountPages(amountPages);
        if (pagination.getAmountPages() < pagination.getCurrentPage()) {
            pagination.setCurrentPage(1);
        }
        return pagination;
    }

    private ShopsFilterDTO setFilter(ShopsFilterDTO filter) {
        if (filter == null) {
            filter = new ShopsFilterDTO();
        }
        if (filter.getLocation() == null) {
            filter.setLocation(DEFAULT_FILTER_SHOPS_LOCATION);
        }
        return filter;
    }

}
