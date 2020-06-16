package ru.mail.dimaushenko.service;

import java.util.List;
import ru.mail.dimaushenko.service.model.PaginationDTO;
import ru.mail.dimaushenko.service.model.ShopDTO;
import ru.mail.dimaushenko.service.model.ShopWithItemsDTO;
import ru.mail.dimaushenko.service.model.ShopsFilterDTO;

public interface ShopService {

    List<ShopDTO> getShops();

    List<ShopDTO> getShops(PaginationDTO paginationDTO);

    List<ShopDTO> getShops(PaginationDTO paginationDTO, ShopsFilterDTO filterDTO);

    List<ShopWithItemsDTO> getShopsWithItems();

    ShopDTO addShop(ShopDTO shopDTO);

    Integer getAmountShops();

}
