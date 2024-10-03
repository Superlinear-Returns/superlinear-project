package com.eight.user.module.to;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileSettingTO implements Serializable {

    @NotNull
    private Integer userId;
    private String username;
    private String email;
    private String phoneNumber;
}
