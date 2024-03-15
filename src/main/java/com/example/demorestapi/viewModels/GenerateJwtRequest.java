package com.example.demorestapi.viewModels;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GenerateJwtRequest {
    public int id;
    public String userName;

    public GenerateJwtRequest(int id, String userName) {
        this.id = id;
        this.userName = userName;
    }
}
