package ru.mail.dimaushenko.service.impl;

import org.springframework.stereotype.Component;
import ru.mail.dimaushenko.service.ConverterFacade;
import ru.mail.dimaushenko.service.ItemConverter;
import ru.mail.dimaushenko.service.ShopConverter;

@Component
public class ConverterFacadeImpl implements ConverterFacade {

    private final ItemConverter itemConverter;
    private final ShopConverter shopConverter;

    public ConverterFacadeImpl(ItemConverter itemConverter, ShopConverter shopConverter) {
        this.itemConverter = itemConverter;
        this.shopConverter = shopConverter;
    }

    @Override
    public ItemConverter getItemConverter() {
        return itemConverter;
    }

    @Override
    public ShopConverter getShopConverter() {
        return shopConverter;
    }

}
