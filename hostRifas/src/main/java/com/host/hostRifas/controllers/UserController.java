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

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @Operation(summary = "(Admin) Retorna todos os usuarios", description = "Controller responsável por retornar todos os usuários cadastrados")
    @GetMapping("all")
    public ResponseEntity<List<UserResponse>> getAll(){
        List<UserResponse> responses = this.userService.getAll();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responses);
    }

    @Operation(summary = "(Admin) Retorna o usuario pelo ID", description = "Busca um usuário no banco de dados pelo seu ID que é unico")
    @GetMapping("{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable Long id){
        UserResponse response = this.userService.getById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @Operation(summary = "(Admin) Retorna o usuario pelo Username", description = "Busca um usuário no banco de dados pelo seu Username que é unico")
    @PostMapping("byUsername")
    public ResponseEntity<UserResponse> getByUsername(@RequestBody UserRequest request){
        UserResponse response = this.userService.getByUsername(request.username());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @Operation(summary = "(Admin) Retorna o usuario pelo E-mail", description = "Busca um usuário no banco de dados pelo seu email que é unico")
    @PostMapping("byEmail")
    public ResponseEntity<UserResponse> getByEmail(@RequestBody UserRequest request){
        UserResponse response = this.userService.getByEmail(request.email());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }
    
    @Operation(summary = "Criar novo usuário", description = "Cria um novo usuário no banco de dados do backend passando as seguintes informações (username, email, password, confirmPassword)")
    @PostMapping("new")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request){
        UserResponse response = this.userService.create(request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

}
