package ru.mail.dimaushenko.webmodule.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.mail.dimaushenko.service.UserService;
import ru.mail.dimaushenko.service.model.AddUserDTO;

@Component
public class UserRegistorValidator implements Validator {

    private final UserService userService;

    public UserRegistorValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return AddUserDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        AddUserDTO user = (AddUserDTO) o;
        if (userService.isUsernameFound(user.getUsername())) {
            errors.rejectValue("username", "", "Username is already userd");
        }
        if (!user.getPassword().equals(user.getPasswordConfirm())) {
            errors.rejectValue("password", "", "Passwords don't match");
        }
    }

}
