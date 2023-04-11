package com.example.users;

import javassist.NotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserImplementation implements UserService {
    UserRepo userRepo;

    @PreAuthorize("isAuthenticated()")
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public List<User> getUserByFirstName(String firstName) throws NotFoundException {
        if (userRepo.findUserByFirstName(firstName).isEmpty()) {
            throw new NotFoundException("User doesn't exist.");
        }
        return userRepo.findUserByFirstName(firstName);
    }
}
