package ru.mail.dimaushenko.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import ru.mail.dimaushenko.repository.model.Item;
import ru.mail.dimaushenko.repository.model.Shop;
import ru.mail.dimaushenko.service.model.ItemDTO;
import ru.mail.dimaushenko.service.model.ShopDTO;
import ru.mail.dimaushenko.service.model.ShopWithItemsDTO;
import ru.mail.dimaushenko.service.ItemConverter;
import ru.mail.dimaushenko.service.ShopConverter;

@Component
public class ShopConverterImpl implements ShopConverter {

    private final ItemConverter itemConverter;

    public ShopConverterImpl(ItemConverter itemConverter) {
        this.itemConverter = itemConverter;
    }

    @Override
    public ShopDTO getDTOFromObject(Shop shop) {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setId(shop.getId());
        shopDTO.setName(shop.getName());
        shopDTO.setLocation(shop.getLocation());
        return shopDTO;
    }

    @Override
    public List<ShopDTO> getDTOFromObject(List<Shop> shops) {
        List<ShopDTO> shopDTOs = new ArrayList<>();
        for (Shop shop : shops) {
            shopDTOs.add(getDTOFromObject(shop));
        }
        return shopDTOs;
    }

    @Override
    public Shop getObjectFromDTO(ShopDTO shopDTO) {
        Shop shop = new Shop();
        shop.setId(shopDTO.getId());
        shop.setName(shopDTO.getName());
        shop.setLocation(shopDTO.getLocation());
        return shop;
    }

    @Override
    public List<Shop> getObjectFromDTO(List<ShopDTO> shopDTOs) {
        List<Shop> shops = new ArrayList<>();
        for (ShopDTO shopDTO : shopDTOs) {
            shops.add(getObjectFromDTO(shopDTO));
        }
        return shops;
    }

    @Override
    public ShopWithItemsDTO getShopWithItemsDTOFromObject(Shop shop) {
        ShopWithItemsDTO shopWithItemsDTO = new ShopWithItemsDTO();
        shopWithItemsDTO.setId(shop.getId());
        shopWithItemsDTO.setName(shop.getName());
        shopWithItemsDTO.setLocation(shop.getLocation());

        List<ItemDTO> items = itemConverter.getDTOFromObject(shop.getItems());
        shopWithItemsDTO.setItems(items);
        return shopWithItemsDTO;
    }

    @Override
    public List<ShopWithItemsDTO> getShopWithItemsDTOFromObject(List<Shop> shops) {
        List<ShopWithItemsDTO> shopWithItemsDTOs = new ArrayList<>();
        for (Shop shop : shops) {
            shopWithItemsDTOs.add(getShopWithItemsDTOFromObject(shop));
        }
        return shopWithItemsDTOs;
    }

    @Override
    public Shop getShopFromShopWithItemsDTO(ShopWithItemsDTO shopDTO) {
        Shop shop = new Shop();
        shop.setId(shopDTO.getId());
        shop.setName(shopDTO.getName());
        shop.setLocation(shopDTO.getLocation());

        List<Item> items = itemConverter.getObjectFromDTO(shopDTO.getItems());
        shop.setItems(items);
        return shop;
    }

    @Override
    public List<Shop> getShopFromShopWithItemsDTO(List<ShopWithItemsDTO> shopDTOs) {
        List<Shop> shops = new ArrayList<>();
        for (ShopWithItemsDTO shopDTO : shopDTOs) {
            shops.add(getShopFromShopWithItemsDTO(shopDTO));
        }
        return shops;
    }

}
