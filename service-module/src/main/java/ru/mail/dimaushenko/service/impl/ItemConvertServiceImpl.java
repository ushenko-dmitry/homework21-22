package ru.mail.dimaushenko.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import ru.mail.dimaushenko.repository.model.Item;
import ru.mail.dimaushenko.repository.model.ItemDetails;
import ru.mail.dimaushenko.service.model.ItemDTO;
import ru.mail.dimaushenko.service.ItemConvertService;
import ru.mail.dimaushenko.service.model.AddItemDTO;
import ru.mail.dimaushenko.service.model.ItemDetailsDTO;
import ru.mail.dimaushenko.service.model.ItemWithShopsDTO;

@Component
public class ItemConvertServiceImpl implements ItemConvertService {

    @Override
    public ItemDTO getDTOFromObject(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setDescription(item.getDescription());
        ItemDetailsDTO itemDetailsDTO = new ItemDetailsDTO();
        itemDetailsDTO.setId(item.getItemDetails().getItemId());
        itemDetailsDTO.setPrice(item.getItemDetails().getPrice());
        itemDTO.setItemDetails(itemDetailsDTO);
        return itemDTO;
    }

    @Override
    public List<ItemDTO> getDTOFromObject(List<Item> items) {
        List<ItemDTO> itemDTOs = new ArrayList<>();
        for (Item item : items) {
            itemDTOs.add(getDTOFromObject(item));
        }
        return itemDTOs;
    }

    @Override
    public Item getObjectFromDTO(ItemDTO itemDTO) {
        Item item = new Item();
        item.setId(itemDTO.getId());
        item.setName(itemDTO.getName());
        item.setDescription(itemDTO.getDescription());
        return item;
    }

    @Override
    public List<Item> getObjectFromDTO(List<ItemDTO> itemDTOs) {
        List<Item> items = new ArrayList<>();
        for (ItemDTO itemDTO : itemDTOs) {
            items.add(getObjectFromDTO(itemDTO));
        }
        return items;
    }

    @Override
    public Item getObjectFromDTO(AddItemDTO itemDTO) {
        Item item = new Item();
        item.setId(itemDTO.getId());
        item.setName(itemDTO.getName());
        item.setDescription(itemDTO.getDescription());

        ItemDetails itemDetails = new ItemDetails();
        itemDetails.setItem(item);
        itemDetails.setPrice(itemDTO.getItemDetails().getPrice());
        item.setItemDetails(itemDetails);
        return item;
    }

    @Override
    public ItemWithShopsDTO getItemWithShopsDTOFromItem(Item item) {
        ItemWithShopsDTO itemWithShopsDTOs = new ItemWithShopsDTO();
        itemWithShopsDTOs.setId(item.getId());
        itemWithShopsDTOs.setName(item.getName());
        itemWithShopsDTOs.setDescription(item.getDescription());

        ItemDetailsDTO itemDetailsDTO = new ItemDetailsDTO();
        itemDetailsDTO.setId(item.getItemDetails().getItemId());
        itemDetailsDTO.setPrice(item.getItemDetails().getPrice());
        itemWithShopsDTOs.setItemDetails(itemDetailsDTO);
        return itemWithShopsDTOs;
    }

}
