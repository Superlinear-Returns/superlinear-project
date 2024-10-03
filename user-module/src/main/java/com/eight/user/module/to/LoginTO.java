package com.eight.user.module.to;

import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginTO implements Serializable {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public LoginTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
