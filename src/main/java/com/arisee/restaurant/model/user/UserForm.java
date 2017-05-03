package com.arisee.restaurant.model.user;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
@Data
public class UserForm {
    @NotBlank
    private String fullName;
    @NotBlank
    private String userName;
    @NotBlank
    private String passWord;
}
