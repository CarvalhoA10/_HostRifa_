package com.host.hostRifas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.host.hostRifas.helpers.requests.LoginRequest;
import com.host.hostRifas.models.user.UserModel;
import com.host.hostRifas.services.security.TokenService;

@RestController
@RequestMapping("user")
public class LoginController {
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginDto){
        System.out.println(loginDto.getEmail());
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

        var authenticate = this.authenticationManager.authenticate(usernamePassword);
        var token = this.tokenService.generateToken((UserModel) authenticate.getPrincipal());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(token);
    }

}
