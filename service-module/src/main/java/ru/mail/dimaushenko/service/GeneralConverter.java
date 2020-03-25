package ru.mail.dimaushenko.service;

import java.util.List;

public interface GeneralConvertService<DTO, O> {

    DTO getDTOFromObject(O model);

    List<DTO> getDTOFromObject(List<O> models);

    O getObjectFromDTO(DTO modelDTO);

    List<O> getObjectFromDTO(List<DTO> modelDTOs);

}
