package ru.mail.dimaushenko.service;

import java.util.List;
import ru.mail.dimaushenko.service.model.ShopDTO;
import ru.mail.dimaushenko.service.model.ShopWithItemsDTO;

public interface ShopService {

    List<ShopDTO> getShops();

    List<ShopWithItemsDTO> getShopsWithItems();

    ShopDTO addShop(ShopDTO shopDTO);

}
