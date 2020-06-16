package ru.mail.dimaushenko.service.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddUserDTO {

    @NotNull
    @Size(min = 1, max = 40)
    private String username;
    @NotNull
    @Size(min = 1)
    private String password;
    @NotNull
    @Size(min = 1)
    private String passwordConfirm;
    @NotNull
    private UserRoleEnumDTO role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public UserRoleEnumDTO getRole() {
        return role;
    }

    public void setRole(UserRoleEnumDTO role) {
        this.role = role;
    }

}
