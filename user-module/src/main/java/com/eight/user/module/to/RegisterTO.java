package com.eight.user.module.to;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterTO implements Serializable {
    private String username;
    private String password;
    private Integer gender;
    private String ipAddress;
    private String userAgent;
}
