package com.example.demorestapi.viewModels;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {
    private String userName;
    private String password;
    private String email;
    private String numberPhone;

    public CreateUserRequest(String userName, String email, String numberPhone, String password) {
        this.userName = userName;
        this.email = email;
        this.numberPhone = numberPhone;
        this.password = password;
    }
}
