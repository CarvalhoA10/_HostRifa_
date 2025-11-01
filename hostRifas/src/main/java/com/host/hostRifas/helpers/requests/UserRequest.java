package com.host.hostRifas.helpers.requests;

public record UserRequest(

    Long id,
    String username,
    String password,
    String email,
    String confirmPassword

) {
    
}
