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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.mail.dimaushenko.service.ItemService;
import ru.mail.dimaushenko.service.ShopService;
import ru.mail.dimaushenko.service.model.AddItemDTO;
import ru.mail.dimaushenko.service.model.ItemDTO;
import ru.mail.dimaushenko.service.model.ItemWithShopsDTO;
import ru.mail.dimaushenko.service.model.ShopDTO;

@Controller
public class ItemController {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private final ItemService itemService;
    private final ShopService shopService;

    public ItemController(ItemService itemService, ShopService shopService) {
        this.itemService = itemService;
        this.shopService = shopService;
    }

    @GetMapping("/items")
    public String getItems(Model model) {
        List<ItemDTO> itemDTOs = itemService.getItemWithItemDetails();
        model.addAttribute("items", itemDTOs);

        model.addAttribute("new_item", new AddItemDTO());
        return "items";
    }

    @GetMapping("/items/{id}")
    public String getItemById(
            @PathVariable(name = "id") Long id,
            Model model
    ) {
        ItemWithShopsDTO itemWithShopsById = itemService.getItemWithShopsById(id);
        model.addAttribute("item", itemWithShopsById);
        return "item";
    }

    @GetMapping("/add_item")
    public String addItemWithShop(Model model) {
        model.addAttribute("new_item", new AddItemDTO());
        List<ShopDTO> shops = shopService.getShops();
        model.addAttribute("shops", shops);
        return "add_item";
    }

    @PostMapping("/add_item")
    public String addItemWithShop(
            @Valid @ModelAttribute(name = "new_item") AddItemDTO item,
            BindingResult bindingResult,
            Model model
    ) {
        List<ShopDTO> shops = shopService.getShops();
        model.addAttribute("shops", shops);
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError error : allErrors) {
                logger.error("Error with add item: " + error.getObjectName() + " - " + error.getDefaultMessage());
            }
            return "add_item";
        }
        itemService.addItemWithShop(item);
        return "redirect:/items";
    }

    @GetMapping("/items/{id}/remove")
    public String removeItemById(
            @PathVariable(name = "id") Long id,
            Model model) {
        itemService.removeItem(id);
        return "redirect:/items";
    }
}
