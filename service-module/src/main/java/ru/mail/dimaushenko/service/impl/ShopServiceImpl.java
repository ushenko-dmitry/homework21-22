package ru.mail.dimaushenko.service.impl;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.mail.dimaushenko.repository.ShopRepository;
import ru.mail.dimaushenko.repository.model.Shop;
import ru.mail.dimaushenko.service.ShopConvertService;
import ru.mail.dimaushenko.service.ShopService;
import ru.mail.dimaushenko.service.model.ShopDTO;
import ru.mail.dimaushenko.service.model.ShopWithItemsDTO;

@Service
@Transactional
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;
    private final ShopConvertService shopConvertService;

    public ShopServiceImpl(ShopRepository shopRepository, ShopConvertService shopConvertService) {
        this.shopRepository = shopRepository;
        this.shopConvertService = shopConvertService;
    }

    @Override
    public List<ShopDTO> getShops() {
        List<Shop> shops = shopRepository.findAll();
        List<ShopDTO> shopDTOs = shopConvertService.getDTOFromObject(shops);
        return shopDTOs;
    }

    @Override
    public List<ShopWithItemsDTO> getShopsWithItems() {
        List<Shop> shops = shopRepository.findAll();
        List<ShopWithItemsDTO> shopDTOs = shopConvertService.getShopWithItemsDTOFromObject(shops);
        return shopDTOs;
    }

    @Override
    public ShopDTO addShop(ShopDTO shopDTO) {
        Shop shop = shopConvertService.getObjectFromDTO(shopDTO);
        shopRepository.persist(shop);
        return shopConvertService.getDTOFromObject(shop);
    }

}
