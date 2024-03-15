package com.example.demorestapi.viewModels;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserLoginVM {
    public String jwToken;
    public UserLoginVM(String jwToken) {
        this.jwToken = jwToken;
    }
}
