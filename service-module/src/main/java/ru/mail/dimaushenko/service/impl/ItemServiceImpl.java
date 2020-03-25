package ru.mail.dimaushenko.service.impl;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.mail.dimaushenko.repository.ItemRepository;
import ru.mail.dimaushenko.repository.ShopRepository;
import ru.mail.dimaushenko.repository.model.Item;
import ru.mail.dimaushenko.repository.model.Shop;
import ru.mail.dimaushenko.service.ConverterFacade;
import ru.mail.dimaushenko.service.ItemConverter;
import ru.mail.dimaushenko.service.ItemService;
import ru.mail.dimaushenko.service.ShopConverter;
import ru.mail.dimaushenko.service.model.AddItemDTO;
import ru.mail.dimaushenko.service.model.ItemDTO;
import ru.mail.dimaushenko.service.model.ItemWithShopsDTO;
import ru.mail.dimaushenko.service.model.ShopDTO;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ShopRepository shopRepository;
    private final ConverterFacade converter;

    public ItemServiceImpl(
            ItemRepository itemRepository,
            ShopRepository shopRepository,
            ConverterFacade converterFacade
    ) {
        this.itemRepository = itemRepository;
        this.shopRepository = shopRepository;
        this.converter = converterFacade;
    }

    @Override
    public void addItemWithShop(AddItemDTO itemDTO) {
        Shop shop = shopRepository.findById(itemDTO.getShopId());
        ItemConverter itemConverter = converter.getItemConverter();
        Item item = itemConverter.getObjectFromDTO(itemDTO);
        shop.getItems().add(item);
        ShopConverter shopConverter = converter.getShopConverter();
        shopConverter.getShopWithItemsDTOFromObject(shop);
    }

    @Override
    public List<ItemDTO> getItemWithItemDetails() {
        List<Item> items = itemRepository.findAll();
        ItemConverter itemConverter = converter.getItemConverter();
        List<ItemDTO> itemDTOs = itemConverter.getDTOFromObject(items);
        return itemDTOs;
    }

    @Override
    public ItemWithShopsDTO getItemWithShopsById(Long id) {
        Item item = itemRepository.findById(id);
        ItemConverter itemConverter = converter.getItemConverter();
        ItemWithShopsDTO itemWithShopsDTOs = itemConverter.getItemWithShopsDTOFromItem(item);
        List<Shop> shops = shopRepository.findAll();
        ShopConverter shopConverter = converter.getShopConverter();
        for (Shop shop : shops) {
            for (Item shopItem : shop.getItems()) {
                if (shopItem.getId().equals(id)) {
                    ShopDTO shopDTO = shopConverter.getDTOFromObject(shop);
                    itemWithShopsDTOs.getShops().add(shopDTO);
                }
            }
        }

        return itemWithShopsDTOs;
    }

    @Override
    public void removeItem(Long id) {
        Item item = itemRepository.findById(id);
        if (item != null) {
            List<Shop> shops = shopRepository.findAll();
            for (Shop shop : shops) {
                shop.getItems().remove(item);
            }
            itemRepository.remove(item);
        }
    }

}
