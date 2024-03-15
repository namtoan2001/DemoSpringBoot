package com.example.demorestapi.interfaces;

import com.example.demorestapi.viewModels.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IUserService {
    List<UserVM> getAllData();

    UserVM getDataWithToken();

    CompletableFuture<Integer> CreateNewUser(CreateUserRequest request);

    CompletableFuture<Boolean> DeleteUser(int id);

    CompletableFuture<Boolean> UpdateUser(UpdateUserRequest request);

    CompletableFuture<UserLoginVM> UserLogin(UserLoginRequest request);

}
