package ru.mail.dimaushenko.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import ru.mail.dimaushenko.repository.model.User;
import ru.mail.dimaushenko.repository.model.UserRoleEnum;
import ru.mail.dimaushenko.service.UserConverter;
import ru.mail.dimaushenko.service.model.AddUserDTO;
import ru.mail.dimaushenko.service.model.UserDTO;
import ru.mail.dimaushenko.service.model.UserRoleEnumDTO;

@Component
public class UserConverterImpl implements UserConverter {

    @Override
    public UserDTO getDTOFromObject(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setPassword(user.getPassword());
        userDTO.setUsername(user.getUsername());

        UserRoleEnumDTO role = getUserRoleEnumDTO(user.getRole());
        userDTO.setRole(role);
        return userDTO;
    }

    @Override
    public List<UserDTO> getDTOFromObject(List<User> users) {
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            UserDTO userDTO = getDTOFromObject(user);
            userDTOs.add(userDTO);
        }
        return userDTOs;
    }

    @Override
    public User getObjectFromDTO(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setPassword(userDTO.getPassword());
        user.setUsername(userDTO.getUsername());

        UserRoleEnum role = getUserRoleEnum(userDTO.getRole());
        user.setRole(role);
        return user;
    }

    @Override
    public User getObjectFromDTO(AddUserDTO userDTO) {
        User user = new User();
        user.setPassword(BCrypt.hashpw(userDTO.getPassword(), BCrypt.gensalt(12)));
        user.setUsername(userDTO.getUsername());

        UserRoleEnum role = getUserRoleEnum(userDTO.getRole());
        user.setRole(role);
        return user;
    }

    @Override
    public List<User> getObjectFromDTO(List<UserDTO> userDTOs) {
        List<User> users = new ArrayList<>();
        for (UserDTO userDTO : userDTOs) {
            User user = getObjectFromDTO(userDTO);
            users.add(user);
        }
        return users;
    }

    private UserRoleEnumDTO getUserRoleEnumDTO(UserRoleEnum role) {
        if (role != null) {
            switch (role) {
                case ADMIN:
                    return UserRoleEnumDTO.ADMIN;
                case USER:
                    return UserRoleEnumDTO.USER;
                default:
                    return null;
            }
        }
        return null;
    }

    private UserRoleEnum getUserRoleEnum(UserRoleEnumDTO role) {
        if (role != null) {
            switch (role) {
                case ADMIN:
                    return UserRoleEnum.ADMIN;
                case USER:
                    return UserRoleEnum.USER;
                default:
                    return null;
            }
        }
        return null;
    }
}
