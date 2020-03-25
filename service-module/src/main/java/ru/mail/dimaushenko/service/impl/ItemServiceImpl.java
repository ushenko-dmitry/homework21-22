package ru.mail.dimaushenko.service.impl;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.mail.dimaushenko.repository.ItemRepository;
import ru.mail.dimaushenko.repository.ShopRepository;
import ru.mail.dimaushenko.repository.model.Item;
import ru.mail.dimaushenko.repository.model.Shop;
import ru.mail.dimaushenko.service.ItemConvertService;
import ru.mail.dimaushenko.service.ItemService;
import ru.mail.dimaushenko.service.ShopConvertService;
import ru.mail.dimaushenko.service.model.AddItemDTO;
import ru.mail.dimaushenko.service.model.ItemDTO;
import ru.mail.dimaushenko.service.model.ItemWithShopsDTO;
import ru.mail.dimaushenko.service.model.ShopDTO;
import ru.mail.dimaushenko.service.model.ShopWithItemsDTO;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ShopRepository shopRepository;
    private final ItemConvertService itemConvertService;
    private final ShopConvertService shopConvertService;

    public ItemServiceImpl(
            ItemRepository itemRepository,
            ShopRepository shopRepository,
            ItemConvertService itemConvertService,
            ShopConvertService shopConvertService
    ) {
        this.itemRepository = itemRepository;
        this.shopRepository = shopRepository;
        this.itemConvertService = itemConvertService;
        this.shopConvertService = shopConvertService;
    }

    @Override
    public void addItemWithShop(AddItemDTO itemDTO) {
        Shop shop = shopRepository.findById(itemDTO.getShopId());
        Item item = itemConvertService.getObjectFromDTO(itemDTO);
        shop.getItems().add(item);
        ShopWithItemsDTO shopWithItemsDTO = shopConvertService.getShopWithItemsDTOFromObject(shop);
    }

    @Override
    public List<ItemDTO> getItemWithItemDetails() {
        List<Item> items = itemRepository.findAll();
        List<ItemDTO> itemDTOs = itemConvertService.getDTOFromObject(items);
        return itemDTOs;
    }

    @Override
    public ItemWithShopsDTO getItemWithShopsById(Long id) {
        Item item = itemRepository.findById(id);
        ItemWithShopsDTO itemWithShopsDTOs = itemConvertService.getItemWithShopsDTOFromItem(item);
        boolean isFound = false;
        List<Shop> shops = shopRepository.findAll();
        for (Shop shop : shops) {
            for (Item shopItem : shop.getItems()) {
                if (shopItem.getId().equals(id)) {
                    ShopDTO shopDTO = shopConvertService.getDTOFromObject(shop);
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
