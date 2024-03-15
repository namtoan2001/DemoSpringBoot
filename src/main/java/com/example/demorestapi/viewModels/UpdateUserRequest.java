package com.example.demorestapi.viewModels;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateUserRequest {
    private int id;
    private String password;
    private String email;
    private String numberPhone;
    public UpdateUserRequest(int id, String email, String numberPhone, String password) {
        this.id = id;
        this.email = email;
        this.numberPhone = numberPhone;
        this.password = password;
    }
}
