package ru.mail.dimaushenko.service.impl;

import java.util.List;
import org.springframework.stereotype.Component;
import ru.mail.dimaushenko.repository.model.ShopsFilter;
import ru.mail.dimaushenko.service.ShopsFilterConverter;
import ru.mail.dimaushenko.service.model.ShopsFilterDTO;

@Component
public class ShopsFilterConverterImpl implements ShopsFilterConverter {

    @Override
    public ShopsFilterDTO getDTOFromObject(ShopsFilter model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ShopsFilterDTO> getDTOFromObject(List<ShopsFilter> models) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ShopsFilter getObjectFromDTO(ShopsFilterDTO shopsFilterDTO) {
        ShopsFilter shopsFilter = new ShopsFilter();
        shopsFilter.setLocation(shopsFilterDTO.getLocation());
        return shopsFilter;
    }

    @Override
    public List<ShopsFilter> getObjectFromDTO(List<ShopsFilterDTO> modelDTOs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
