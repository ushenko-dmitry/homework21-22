package ru.mail.dimaushenko.service.utils;

import ru.mail.dimaushenko.service.model.PaginationDTO;

public class PaginationUtils {

    public static Integer getStartElement(PaginationDTO paginationDTO) {
        return (paginationDTO.getCurrentPage() - 1) * paginationDTO.getElementsPerPage();
    }

}
