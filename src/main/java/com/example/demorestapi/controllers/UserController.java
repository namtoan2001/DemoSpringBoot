package com.example.demorestapi.controllers;

import com.example.demorestapi.services.UserService;
import com.example.demorestapi.viewModels.CreateUserRequest;
import com.example.demorestapi.viewModels.UpdateUserRequest;
import com.example.demorestapi.viewModels.UserLoginRequest;
import com.example.demorestapi.viewModels.UserVM;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@CrossOrigin(origins = {"https://demospringboot-production-7e99.up.railway.app", "http://demospringboot-production-7e99.up.railway.app"}, allowCredentials = "true")
@SecurityRequirement(name = "Authorization")
@RequestMapping("/api/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAllData")
    public List<UserVM> getAllUser() {
        var result = userService.getAllData().stream().toList();
        logger.info("List of users before JSON conversion: {}", result);
        return result;
    }

    @GetMapping("/getDataWithToken")
    public UserVM getDataWithToken() {
        UserVM result;
        result = userService.getDataWithToken();
        return result;
    }

    @PostMapping("/CreateNewUser")
    public CompletableFuture<ResponseEntity<?>> CreateNewUser(@RequestBody CreateUserRequest request) {
        return userService.CreateNewUser(request)
                .thenApply(result -> {
                    if (result == 0) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Add Service was unsuccessful!");
                    }
                    return ResponseEntity.ok(result);
                });
    }

    @DeleteMapping("/DeleteUser/{id}")
    public CompletableFuture<ResponseEntity<String>> DeleteUser(int id) {
        return userService.DeleteUser(id)
                .thenApply(result -> {
                    if (!result) {
                        return ResponseEntity.badRequest().body("Delete was unsuccessful!");
                    }
                    return ResponseEntity.ok("Delete was successful!");
                })
                .exceptionally(error ->
                        ResponseEntity.status(500).body("Error retrieving data from the database: " + error.getMessage())
                );
    }

    @PutMapping("/UpdateUser")
    public CompletableFuture<ResponseEntity<String>> UpdateUser(@RequestBody UpdateUserRequest request) {
        return userService.UpdateUser(request)
                .thenApply(result -> {
                    if (!result) {
                        return ResponseEntity.badRequest().body("Delete was unsuccessful!");
                    }
                    return ResponseEntity.ok("Update was successful!");
                })
                .exceptionally(error ->
                        ResponseEntity.status(500).body("Error retrieving data from the database: " + error.getMessage())
                );
    }

    @PostMapping("/UserLogin")
    public CompletableFuture<ResponseEntity<?>> UserLogin(@RequestBody UserLoginRequest request) {
        return userService.UserLogin(request)
                .thenApply(result -> {
                    if (result == null) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Login failed!");
                    }
                    return ResponseEntity.ok(result);
                });
    }
}