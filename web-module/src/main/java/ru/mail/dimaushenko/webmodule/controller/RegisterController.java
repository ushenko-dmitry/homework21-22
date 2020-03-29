package ru.mail.dimaushenko.webmodule.controller;

import java.lang.invoke.MethodHandles;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mail.dimaushenko.service.UserService;
import ru.mail.dimaushenko.service.model.AddUserDTO;
import ru.mail.dimaushenko.webmodule.validator.UserRegistorValidator;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private final UserService userService;
    private final UserRegistorValidator userRegistorValidator;

    public RegisterController(UserService userService, UserRegistorValidator userRegistorValidator) {
        this.userService = userService;
        this.userRegistorValidator = userRegistorValidator;
    }

    @GetMapping
    public String registerUser(Model model) {
        model.addAttribute("new_user", new AddUserDTO());
        return "register";
    }

    @PostMapping
    public String registerUser(
            @ModelAttribute(name = "new_user") AddUserDTO addUser,
            BindingResult bindingResult,
            Model model
    ) {
        userRegistorValidator.validate(addUser, bindingResult);
        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                List<ObjectError> allErrors = bindingResult.getAllErrors();
                logErrors(allErrors);
            }
            return "register";
        }
        userService.addUser(addUser);
        return "redirect:/";
    }

    private void logErrors(List<ObjectError> allErrors) {
        for (ObjectError error : allErrors) {
            logger.error("Error with add item: " + error.getObjectName() + " - " + error.getDefaultMessage());
        }
    }

}
