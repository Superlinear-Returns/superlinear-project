package com.eight.user.module.to;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfile implements Serializable {
    private String username;
    private String email;
    private String phoneNumber;
    private KycInfo kycInfo;
}
