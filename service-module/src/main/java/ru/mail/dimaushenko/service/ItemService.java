package ru.mail.dimaushenko.service;

import java.util.List;
import ru.mail.dimaushenko.service.model.AddItemDTO;
import ru.mail.dimaushenko.service.model.ItemDTO;
import ru.mail.dimaushenko.service.model.ItemWithShopsDTO;
import ru.mail.dimaushenko.service.model.ItemsFilterDTO;
import ru.mail.dimaushenko.service.model.PaginationDTO;

public interface ItemService {

    List<ItemDTO> getItemWithItemDetails();

    List<ItemDTO> getItemWithItemDetails(PaginationDTO paginationDTO);

    List<ItemDTO> getItemWithItemDetails(PaginationDTO pagination, ItemsFilterDTO filter);

    void addItemWithShop(AddItemDTO item);

    ItemWithShopsDTO getItemWithShopsById(Long id);

    void removeItem(Long id);

    Integer getAmountItems();

}
