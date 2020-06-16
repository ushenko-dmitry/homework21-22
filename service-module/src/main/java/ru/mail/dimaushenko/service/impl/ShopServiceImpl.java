package ru.mail.dimaushenko.service.impl;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.mail.dimaushenko.repository.ShopRepository;
import ru.mail.dimaushenko.repository.model.Pagination;
import ru.mail.dimaushenko.repository.model.Shop;
import ru.mail.dimaushenko.repository.model.ShopsFilter;
import ru.mail.dimaushenko.service.ConverterFacade;
import ru.mail.dimaushenko.service.PaginationConverter;
import ru.mail.dimaushenko.service.ShopConverter;
import ru.mail.dimaushenko.service.ShopService;
import ru.mail.dimaushenko.service.ShopsFilterConverter;
import ru.mail.dimaushenko.service.model.PaginationDTO;
import ru.mail.dimaushenko.service.model.ShopDTO;
import ru.mail.dimaushenko.service.model.ShopWithItemsDTO;
import ru.mail.dimaushenko.service.model.ShopsFilterDTO;

@Service
@Transactional
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;
    private final ConverterFacade converterFacade;

    public ShopServiceImpl(
            ShopRepository shopRepository,
            ConverterFacade converterFacade
    ) {
        this.shopRepository = shopRepository;
        this.converterFacade = converterFacade;
    }

    @Override
    public List<ShopDTO> getShops() {
        List<Shop> shops = shopRepository.findAll();
        ShopConverter shopConverter = converterFacade.getShopConverter();
        List<ShopDTO> shopDTOs = shopConverter.getDTOFromObject(shops);
        return shopDTOs;
    }

    @Override
    public List<ShopDTO> getShops(PaginationDTO paginationDTO) {
        PaginationConverter paginationConverter = converterFacade.getPaginationConverter();
        Pagination pagination = paginationConverter.getObjectFromDTO(paginationDTO);
        List<Shop> shops = shopRepository.findLimit(pagination);
        ShopConverter shopConverter = converterFacade.getShopConverter();
        List<ShopDTO> shopDTOs = shopConverter.getDTOFromObject(shops);
        return shopDTOs;
    }

    @Override
    public List<ShopDTO> getShops(PaginationDTO paginationDTO, ShopsFilterDTO filterDTO) {
        PaginationConverter paginationConverter = converterFacade.getPaginationConverter();
        Pagination pagination = paginationConverter.getObjectFromDTO(paginationDTO);
        ShopsFilterConverter shopsFilterConverter = converterFacade.getShopsFilterConverter();
        ShopsFilter filter = shopsFilterConverter.getObjectFromDTO(filterDTO);
        List<Shop> shops = shopRepository.findLimitFilteredBy(pagination, filter);
        ShopConverter shopConverter = converterFacade.getShopConverter();
        List<ShopDTO> shopDTOs = shopConverter.getDTOFromObject(shops);
        return shopDTOs;
    }

    @Override
    public List<ShopWithItemsDTO> getShopsWithItems() {
        List<Shop> shops = shopRepository.findAll();
        ShopConverter shopConverter = converterFacade.getShopConverter();
        List<ShopWithItemsDTO> shopDTOs = shopConverter.getShopWithItemsDTOFromObject(shops);
        return shopDTOs;
    }

    @Override
    public ShopDTO addShop(ShopDTO shopDTO) {
        ShopConverter shopConverter = converterFacade.getShopConverter();
        Shop shop = shopConverter.getObjectFromDTO(shopDTO);
        shopRepository.persist(shop);
        return shopConverter.getDTOFromObject(shop);
    }

    @Override
    public Integer getAmountShops() {
        return shopRepository.getAmountElements();
    }
}
