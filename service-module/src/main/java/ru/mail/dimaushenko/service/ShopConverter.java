package ru.mail.dimaushenko.service;

import java.util.List;
import ru.mail.dimaushenko.repository.model.Shop;
import ru.mail.dimaushenko.service.model.ShopDTO;
import ru.mail.dimaushenko.service.model.ShopWithItemsDTO;

public interface ShopConverter extends GeneralConverter<ShopDTO, Shop> {

    ShopWithItemsDTO getShopWithItemsDTOFromObject(Shop shops);

    List<ShopWithItemsDTO> getShopWithItemsDTOFromObject(List<Shop> shops);

    Shop getShopFromShopWithItemsDTO(ShopWithItemsDTO shopDTOs);

    List<Shop> getShopFromShopWithItemsDTO(List<ShopWithItemsDTO> shopDTOs);

}
