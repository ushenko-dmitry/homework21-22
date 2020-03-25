package ru.mail.dimaushenko.service;

import ru.mail.dimaushenko.repository.model.Item;
import ru.mail.dimaushenko.service.model.AddItemDTO;
import ru.mail.dimaushenko.service.model.ItemDTO;
import ru.mail.dimaushenko.service.model.ItemWithShopsDTO;

public interface ItemConvertService extends GeneralConvertService<ItemDTO, Item> {

    Item getObjectFromDTO(AddItemDTO itemDTO);

    ItemWithShopsDTO getItemWithShopsDTOFromItem(Item item);

}
