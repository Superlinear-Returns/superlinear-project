package com.eight.user.module.to;

import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterTO implements Serializable {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private Integer gender;

    private String ipAddress;

    private String userAgent;
}
