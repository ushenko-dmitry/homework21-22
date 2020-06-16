package ru.mail.dimaushenko.service.impl;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.mail.dimaushenko.repository.ItemRepository;
import ru.mail.dimaushenko.repository.ShopRepository;
import ru.mail.dimaushenko.repository.model.Item;
import ru.mail.dimaushenko.repository.model.ItemsFilter;
import ru.mail.dimaushenko.repository.model.Pagination;
import ru.mail.dimaushenko.repository.model.Shop;
import ru.mail.dimaushenko.service.ConverterFacade;
import ru.mail.dimaushenko.service.ItemConverter;
import ru.mail.dimaushenko.service.ItemService;
import ru.mail.dimaushenko.service.ItemsFilterConverter;
import ru.mail.dimaushenko.service.PaginationConverter;
import ru.mail.dimaushenko.service.ShopConverter;
import ru.mail.dimaushenko.service.model.AddItemDTO;
import ru.mail.dimaushenko.service.model.ItemDTO;
import ru.mail.dimaushenko.service.model.ItemWithShopsDTO;
import ru.mail.dimaushenko.service.model.ItemsFilterDTO;
import ru.mail.dimaushenko.service.model.PaginationDTO;
import ru.mail.dimaushenko.service.model.ShopDTO;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ShopRepository shopRepository;
    private final ConverterFacade converterFacade;

    public ItemServiceImpl(
            ItemRepository itemRepository,
            ShopRepository shopRepository,
            ConverterFacade converterFacade
    ) {
        this.itemRepository = itemRepository;
        this.shopRepository = shopRepository;
        this.converterFacade = converterFacade;
    }

    @Override
    public void addItemWithShop(AddItemDTO itemDTO) {
        ItemConverter itemConverter = converterFacade.getItemConverter();
        Item item = itemConverter.getObjectFromDTO(itemDTO);
        for (Long shopId : itemDTO.getShopId()) {
            Shop shop = shopRepository.findById(shopId);
            shop.getItems().add(item);
            ShopConverter shopConverter = converterFacade.getShopConverter();
            shopConverter.getShopWithItemsDTOFromObject(shop);
        }
    }

    @Override
    public List<ItemDTO> getItemWithItemDetails() {
        List<Item> items = itemRepository.findAll();
        ItemConverter itemConverter = converterFacade.getItemConverter();
        List<ItemDTO> itemDTOs = itemConverter.getDTOFromObject(items);
        return itemDTOs;
    }

    @Override
    public List<ItemDTO> getItemWithItemDetails(PaginationDTO paginationDTO) {
        PaginationConverter paginationConverter = converterFacade.getPaginationConverter();
        Pagination pagination = paginationConverter.getObjectFromDTO(paginationDTO);
        List<Item> items = itemRepository.findLimit(pagination);
        ItemConverter itemConverter = converterFacade.getItemConverter();
        List<ItemDTO> itemDTOs = itemConverter.getDTOFromObject(items);
        return itemDTOs;
    }

    @Override
    public List<ItemDTO> getItemWithItemDetails(PaginationDTO paginationDTO, ItemsFilterDTO itemsFilterDTO) {
        PaginationConverter paginationConverter = converterFacade.getPaginationConverter();
        Pagination pagination = paginationConverter.getObjectFromDTO(paginationDTO);
        ItemsFilterConverter itemsFilterConverter = converterFacade.getItemsFilterConverter();
        ItemsFilter itemsFilter = itemsFilterConverter.getObjectFromDTO(itemsFilterDTO);
        List<Item> items = itemRepository.findLimitFilteredBy(pagination, itemsFilter);
        ItemConverter itemConverter = converterFacade.getItemConverter();
        List<ItemDTO> itemDTOs = itemConverter.getDTOFromObject(items);
        return itemDTOs;
    }

    @Override
    public ItemWithShopsDTO getItemWithShopsById(Long id) {
        Item item = itemRepository.findById(id);
        ItemConverter itemConverter = converterFacade.getItemConverter();
        ItemWithShopsDTO itemWithShopsDTOs = itemConverter.getItemWithShopsDTOFromItem(item);
        List<Shop> shopsWithItem = shopRepository.getShopsWithItem(item);
        ShopConverter shopConverter = converterFacade.getShopConverter();
        for (Shop shop : shopsWithItem) {
            ShopDTO shopDTO = shopConverter.getDTOFromObject(shop);
            itemWithShopsDTOs.getShops().add(shopDTO);
        }
        return itemWithShopsDTOs;
    }

    @Override
    public void removeItem(Long id) {
        Item item = itemRepository.findById(id);
        if (item != null) {
            List<Shop> shops = shopRepository.getShopsWithItem(item);
            for (Shop shop : shops) {
                shop.getItems().remove(item);
            }
            itemRepository.remove(item);
        }
    }

    @Override
    public Integer getAmountItems() {
        return itemRepository.getAmountElements();
    }

}
