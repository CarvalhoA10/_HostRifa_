package com.host.hostRifas.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.host.hostRifas.helpers.requests.UserRequest;
import com.host.hostRifas.helpers.responses.UserResponse;
import com.host.hostRifas.services.user.UserService;

@RestController
@RequestMapping("user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("all")
    public ResponseEntity<List<UserResponse>> getAll(){
        List<UserResponse> responses = this.userService.getAll();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responses);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable Long id){
        UserResponse response = this.userService.getById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @PostMapping("byUsername")
    public ResponseEntity<UserResponse> getByUsername(@RequestBody UserRequest request){
        UserResponse response = this.userService.getByUsername(request.username());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @PostMapping("byEmail")
    public ResponseEntity<UserResponse> getByEmail(@RequestBody UserRequest request){
        UserResponse response = this.userService.getByEmail(request.email());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }
    
    @PostMapping("new")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request){
        UserResponse response = this.userService.create(request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

}
