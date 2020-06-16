package ru.mail.dimaushenko.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.mail.dimaushenko.service.model.AddUserDTO;
import ru.mail.dimaushenko.service.model.UserDTO;

public interface UserService extends UserDetailsService {

    void addUser(AddUserDTO addUser);

    UserDTO getUserByUsername(String username);

    boolean isUsernameFound(String username);

}
