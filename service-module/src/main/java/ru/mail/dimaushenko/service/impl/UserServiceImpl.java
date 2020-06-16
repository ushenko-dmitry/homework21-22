package ru.mail.dimaushenko.service.impl;

import javax.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.mail.dimaushenko.repository.UserRepository;
import ru.mail.dimaushenko.repository.model.User;
import ru.mail.dimaushenko.service.ConverterFacade;
import ru.mail.dimaushenko.service.UserConverter;
import ru.mail.dimaushenko.service.UserService;
import ru.mail.dimaushenko.service.model.AddUserDTO;
import ru.mail.dimaushenko.service.model.AppUser;
import ru.mail.dimaushenko.service.model.UserDTO;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ConverterFacade converterFacade;

    public UserServiceImpl(
            UserRepository userRepository,
            ConverterFacade converterFacade) {
        this.userRepository = userRepository;
        this.converterFacade = converterFacade;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getEntityByUsername(username);
        UserConverter userConverter = converterFacade.getUserConverter();
        UserDTO userDTO = userConverter.getDTOFromObject(user);

        if (userDTO == null) {
            throw new UsernameNotFoundException("User is not found");
        }
        AppUser appUser = new AppUser(userDTO);
        return appUser;
    }

    @Override
    public void addUser(AddUserDTO addUser) {
        UserConverter userConverter = converterFacade.getUserConverter();
        User user = userConverter.getObjectFromDTO(addUser);
        userRepository.persist(user);
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        User user = userRepository.getEntityByUsername(username);
        UserConverter userConverter = converterFacade.getUserConverter();
        UserDTO userDTO = userConverter.getDTOFromObject(user);
        return userDTO;
    }

    @Override
    public boolean isUsernameFound(String username) {
        return userRepository.isUsernameFound(username);
    }

}
