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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.mail.dimaushenko.service.ItemService;
import ru.mail.dimaushenko.service.ShopService;
import ru.mail.dimaushenko.service.model.AddItemDTO;
import ru.mail.dimaushenko.service.model.ItemDTO;
import ru.mail.dimaushenko.service.model.ItemWithShopsDTO;
import ru.mail.dimaushenko.service.model.ItemsFilterDTO;
import ru.mail.dimaushenko.service.model.PaginationDTO;
import ru.mail.dimaushenko.service.model.ShopDTO;
import ru.mail.dimaushenko.service.model.ViewItemsDTO;
import static ru.mail.dimaushenko.webmodule.constants.FilterConstants.DEFAULT_FILTER_ITEMS_NAME;
import static ru.mail.dimaushenko.webmodule.constants.FilterConstants.DEFAULT_FILTER_ITEMS_PRICE_MAX;
import static ru.mail.dimaushenko.webmodule.constants.FilterConstants.DEFAULT_FILTER_ITEMS_PRICE_MIN;
import static ru.mail.dimaushenko.webmodule.constants.PaginationConstants.DEFAULT_CURRENT_PAGE;
import static ru.mail.dimaushenko.webmodule.constants.PaginationConstants.DEFAULT_ITEMS_PER_PAGE;
import ru.mail.dimaushenko.webmodule.validator.AddItemValidator;

@Controller
@RequestMapping("/items")
@SessionAttributes(types = ViewItemsDTO.class)
public class ItemController {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private final ItemService itemService;
    private final ShopService shopService;
    private final AddItemValidator addItemValidator;

    public ItemController(ItemService itemService, ShopService shopService, AddItemValidator addItemValidator) {
        this.itemService = itemService;
        this.shopService = shopService;
        this.addItemValidator = addItemValidator;
    }

    @GetMapping()
    public String getItems(
            ViewItemsDTO viewItemsDTO,
            BindingResult bindingResult,
            Model model
    ) {
        PaginationDTO pagination = setPagination(viewItemsDTO.getPagination());
        viewItemsDTO.setPagination(pagination);
        ItemsFilterDTO filter = setFilter(viewItemsDTO.getFilter());
        viewItemsDTO.setFilter(filter);
        
        List<ItemDTO> itemDTOs = itemService.getItemWithItemDetails(pagination, filter);
        viewItemsDTO.setItems(itemDTOs);

        model.addAttribute("viewItems", viewItemsDTO);
        return "items";
    }

    @GetMapping("/{id}")
    public String getItemById(
            @PathVariable(name = "id") Long id,
            Model model
    ) {
        ItemWithShopsDTO itemWithShopsById = itemService.getItemWithShopsById(id);
        model.addAttribute("item", itemWithShopsById);
        return "item";
    }

    @GetMapping("/add")
    public String addItemWithShop(Model model) {
        model.addAttribute("new_item", new AddItemDTO());
        List<ShopDTO> shops = shopService.getShops();
        model.addAttribute("shops", shops);
        return "add_item";
    }

    @PostMapping("/add")
    public String addItemWithShop(
            @Valid @ModelAttribute(name = "new_item") AddItemDTO item,
            BindingResult bindingResult,
            Model model
    ) {
        List<ShopDTO> shops = shopService.getShops();
        model.addAttribute("shops", shops);
        addItemValidator.validate(item, bindingResult);
        if (bindingResult.hasErrors()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            logErrors(allErrors);
            return "add_item";
        }
        itemService.addItemWithShop(item);
        return "redirect:/items";
    }

    @GetMapping("/{id}/remove")
    public String removeItemById(
            @PathVariable(name = "id") Long id,
            Model model) {
        itemService.removeItem(id);
        return "redirect:/items";
    }

    private void logErrors(List<ObjectError> allErrors) {
        for (ObjectError error : allErrors) {
            logger.error("Error with add item: " + error.getObjectName() + " - " + error.getDefaultMessage());
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
        Integer amountItems = itemService.getAmountItems();
        Integer amountPages = amountItems / pagination.getElementsPerPage();
        if (amountItems % pagination.getElementsPerPage() > 0) {
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

    private ItemsFilterDTO setFilter(ItemsFilterDTO filter) {
        if (filter == null) {
            filter = new ItemsFilterDTO();
        }
        if (filter.getName() == null) {
            filter.setName(DEFAULT_FILTER_ITEMS_NAME);
        }
        if (filter.getPriceMin() == null) {
            filter.setPriceMin(DEFAULT_FILTER_ITEMS_PRICE_MIN);
        }
        if (filter.getPriceMax() == null) {
            filter.setPriceMax(DEFAULT_FILTER_ITEMS_PRICE_MAX);
        }
        return filter;
    }

}
