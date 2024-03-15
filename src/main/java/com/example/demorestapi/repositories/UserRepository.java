package com.example.demorestapi.repositories;

import com.example.demorestapi.models.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    @NotNull List<User> findAll();
    User findByUserName(String userName);
    User findById(int id);
}
