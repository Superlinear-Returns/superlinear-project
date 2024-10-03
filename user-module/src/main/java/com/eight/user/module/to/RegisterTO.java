package com.eight.user.module.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterTO implements Serializable {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotNull
    private Integer gender;

    @NotNull
    private LocalDate dateOfBirth;

    private String ipAddress;

    private String userAgent;

}
