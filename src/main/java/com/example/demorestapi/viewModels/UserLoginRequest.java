package com.example.demorestapi.viewModels;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequest {
    public String userName;
    public String password;

    public UserLoginRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
