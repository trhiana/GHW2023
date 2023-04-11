package com.example.users;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo {
    public List<User> findAll();

    public List<User> findUserByFirstName(String firstName);
}
