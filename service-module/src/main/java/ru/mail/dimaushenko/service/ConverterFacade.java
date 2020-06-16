package ru.mail.dimaushenko.service;

public interface ConverterFacade {

    ItemConverter getItemConverter();

    ShopConverter getShopConverter();

    UserConverter getUserConverter();

    PaginationConverter getPaginationConverter();

    ItemsFilterConverter getItemsFilterConverter();

    ShopsFilterConverter getShopsFilterConverter();

}
