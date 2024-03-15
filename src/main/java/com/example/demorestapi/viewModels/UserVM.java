package com.example.demorestapi.viewModels;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVM {
    private int id;
    private String userName;
    private String email;
    private String numberPhone;
    public UserVM(int id, String userName, String email, String numberPhone) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.numberPhone = numberPhone;
    }
}
