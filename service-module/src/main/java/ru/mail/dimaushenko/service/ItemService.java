package ru.mail.dimaushenko.service;

import java.util.List;
import ru.mail.dimaushenko.service.model.AddItemDTO;
import ru.mail.dimaushenko.service.model.ItemDTO;
import ru.mail.dimaushenko.service.model.ItemWithShopsDTO;

public interface ItemService {

    List<ItemDTO> getItemWithItemDetails();

    void addItemWithShop(AddItemDTO item);

    ItemWithShopsDTO getItemWithShopsById(Long id);

    void removeItem(Long id);

}
