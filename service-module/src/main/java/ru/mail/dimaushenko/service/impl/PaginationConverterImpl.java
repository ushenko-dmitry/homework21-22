package ru.mail.dimaushenko.service.impl;

import java.util.List;
import org.springframework.stereotype.Component;
import ru.mail.dimaushenko.repository.model.Pagination;
import ru.mail.dimaushenko.service.PaginationConverter;
import ru.mail.dimaushenko.service.model.PaginationDTO;
import static ru.mail.dimaushenko.service.utils.PaginationUtils.getStartElement;

@Component
public class PaginationConverterImpl implements PaginationConverter {

    @Override
    public PaginationDTO getDTOFromObject(Pagination model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PaginationDTO> getDTOFromObject(List<Pagination> models) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pagination getObjectFromDTO(PaginationDTO paginationDTO) {
        Pagination pagination = new Pagination();
        pagination.setElementsPerPage(paginationDTO.getElementsPerPage());
        pagination.setStartElement(getStartElement(paginationDTO));
        return pagination;
    }

    @Override
    public List<Pagination> getObjectFromDTO(List<PaginationDTO> modelDTOs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
