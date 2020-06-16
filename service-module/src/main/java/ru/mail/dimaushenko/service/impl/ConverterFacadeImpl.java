package ru.mail.dimaushenko.service.impl;

import org.springframework.stereotype.Component;
import ru.mail.dimaushenko.service.ConverterFacade;
import ru.mail.dimaushenko.service.ItemConverter;
import ru.mail.dimaushenko.service.ItemsFilterConverter;
import ru.mail.dimaushenko.service.PaginationConverter;
import ru.mail.dimaushenko.service.ShopConverter;
import ru.mail.dimaushenko.service.ShopsFilterConverter;
import ru.mail.dimaushenko.service.UserConverter;

@Component
public class ConverterFacadeImpl implements ConverterFacade {

    private final ItemConverter itemConverter;
    private final ShopConverter shopConverter;
    private final UserConverter userConverter;
    private final PaginationConverter paginationConverter;
    private final ItemsFilterConverter itemsFilterConverter;
    private final ShopsFilterConverter shopsFilterConverter;

    public ConverterFacadeImpl(
            ItemConverter itemConverter,
            ShopConverter shopConverter,
            UserConverter userConverter,
            PaginationConverter paginationConverter,
            ItemsFilterConverter itemsFilterConverter,
            ShopsFilterConverter shopsFilterConverter
    ) {
        this.itemConverter = itemConverter;
        this.shopConverter = shopConverter;
        this.userConverter = userConverter;
        this.paginationConverter = paginationConverter;
        this.itemsFilterConverter = itemsFilterConverter;
        this.shopsFilterConverter = shopsFilterConverter;
    }

    @Override
    public PaginationConverter getPaginationConverter() {
        return paginationConverter;
    }

    @Override
    public ItemConverter getItemConverter() {
        return itemConverter;
    }

    @Override
    public ShopConverter getShopConverter() {
        return shopConverter;
    }

    @Override
    public UserConverter getUserConverter() {
        return userConverter;
    }

    @Override
    public ItemsFilterConverter getItemsFilterConverter() {
        return itemsFilterConverter;
    }

    @Override
    public ShopsFilterConverter getShopsFilterConverter() {
        return shopsFilterConverter;
    }

}
