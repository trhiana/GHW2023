package com.example.users;

import javassist.NotFoundException;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();

    public List<User> getUserByFirstName(String firstName) throws NotFoundException;
}
