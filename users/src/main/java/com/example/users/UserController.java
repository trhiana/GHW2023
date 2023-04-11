package com.example.users;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/")
    public ResponseEntity<List<User>> displayUsers() {
        List<User> result = userService.getAllUsers();
        return new ResponseEntity<List<User>>(result, HttpStatus.OK);
    }

    @GetMapping("/find/{firstName}")
    public ResponseEntity<List<User>> findUser(@PathVariable("firstName") String firstName) throws NotFoundException {
        List<User> result = userService.getUserByFirstName(firstName);
        return new ResponseEntity<List<User>>(result, HttpStatus.OK);
    }
}