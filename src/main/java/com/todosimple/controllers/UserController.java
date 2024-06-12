package com.todosimple.controllers;


import com.todosimple.models.User;
import com.todosimple.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
@Validated
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping({"/id"})
    public ResponseEntity<User> findById(@PathVariable Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        List<User> users = userService.findAll();
        return ResponseEntity.ok().body(users);

    }

    @PostMapping
    @Validated(User.CreatedUser.class)
    public ResponseEntity<Void> createUser(@Valid @RequestBody User user) {

        User newUser = userService.create(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping({"/id"})
    public ResponseEntity<User> updateUser(@Valid @RequestBody User obj, @PathVariable Long id){
        obj.setId(id);
        this.userService.create(obj);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping({"/id"})
    public ResponseEntity<User> deleteUser(@Valid @PathVariable Long id){
        this.userService.delete(id);
        return ResponseEntity.noContent().build();

    }

}
