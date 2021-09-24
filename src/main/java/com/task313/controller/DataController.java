package com.task313.controller;

import com.task313.model.User;
import com.task313.service.RoleService;
import com.task313.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DataController {
    private final UserService userService;

    @Autowired
    public DataController(UserService userService) { this.userService = userService; }

    @PostMapping(path ="/admin/users")
    public ResponseEntity<Void> create(@RequestBody User user) {
        userService.create(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path = "/user")
    public ResponseEntity<User> getAuth() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(userService.read(user.getId()), HttpStatus.OK);
    }

    @GetMapping(path = "/admin/users/{id}")
    public ResponseEntity<User> readById(@PathVariable long id) {
        return new ResponseEntity<>(userService.read(id), HttpStatus.OK);
    }

    @GetMapping(path = "/admin/users")
    public ResponseEntity<List<User>> readAll() {
        return new ResponseEntity<>(userService.readAll(), HttpStatus.OK);
    }

    @PutMapping(path = "/admin/users/{id}")
    public ResponseEntity<Void> update(@RequestBody User user) {
        userService.update(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping (path = "/admin/users/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
