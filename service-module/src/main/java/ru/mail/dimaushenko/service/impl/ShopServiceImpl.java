package ru.mail.dimaushenko.service.impl;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.mail.dimaushenko.repository.ShopRepository;
import ru.mail.dimaushenko.repository.model.Shop;
import ru.mail.dimaushenko.service.ConverterFacade;
import ru.mail.dimaushenko.service.ShopConverter;
import ru.mail.dimaushenko.service.ShopService;
import ru.mail.dimaushenko.service.model.ShopDTO;
import ru.mail.dimaushenko.service.model.ShopWithItemsDTO;

@Service
@Transactional
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;
    private final ConverterFacade converter;

    public ShopServiceImpl(
            ShopRepository shopRepository,
            ConverterFacade converterFacade
    ) {
        this.shopRepository = shopRepository;
        this.converter = converterFacade;
    }

    @Override
    public List<ShopDTO> getShops() {
        List<Shop> shops = shopRepository.findAll();
        ShopConverter shopConverter = converter.getShopConverter();
        List<ShopDTO> shopDTOs = shopConverter.getDTOFromObject(shops);
        return shopDTOs;
    }

    @Override
    public List<ShopWithItemsDTO> getShopsWithItems() {
        List<Shop> shops = shopRepository.findAll();
        ShopConverter shopConverter = converter.getShopConverter();
        List<ShopWithItemsDTO> shopDTOs = shopConverter.getShopWithItemsDTOFromObject(shops);
        return shopDTOs;
    }

    @Override
    public ShopDTO addShop(ShopDTO shopDTO) {
        ShopConverter shopConverter = converter.getShopConverter();
        Shop shop = shopConverter.getObjectFromDTO(shopDTO);
        shopRepository.persist(shop);
        return shopConverter.getDTOFromObject(shop);
    }

}
