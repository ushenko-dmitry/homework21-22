package ru.mail.dimaushenko.service.impl;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Component;
import ru.mail.dimaushenko.repository.model.ItemsFilter;
import ru.mail.dimaushenko.service.ItemsFilterConverter;
import ru.mail.dimaushenko.service.model.ItemsFilterDTO;

@Component
public class ItemsFilterConverterImpl implements ItemsFilterConverter {

    @Override
    public ItemsFilterDTO getDTOFromObject(ItemsFilter model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ItemsFilterDTO> getDTOFromObject(List<ItemsFilter> models) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItemsFilter getObjectFromDTO(ItemsFilterDTO itemsFilterDTO) {
        ItemsFilter itemsFilter = new ItemsFilter();
        itemsFilter.setName(itemsFilterDTO.getName());

        BigDecimal priceMin = new BigDecimal(itemsFilterDTO.getPriceMin());
        BigDecimal priceMax = new BigDecimal(itemsFilterDTO.getPriceMax());
        itemsFilter.setPriceMin(priceMin);
        itemsFilter.setPriceMax(priceMax);
        return itemsFilter;
    }

    @Override
    public List<ItemsFilter> getObjectFromDTO(List<ItemsFilterDTO> modelDTOs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
