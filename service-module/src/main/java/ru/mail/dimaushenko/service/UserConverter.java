package ru.mail.dimaushenko.service;

import ru.mail.dimaushenko.repository.model.User;
import ru.mail.dimaushenko.service.model.AddUserDTO;
import ru.mail.dimaushenko.service.model.UserDTO;

public interface UserConverter extends GeneralConverter<UserDTO, User> {

    User getObjectFromDTO(AddUserDTO modelDTO);

}
