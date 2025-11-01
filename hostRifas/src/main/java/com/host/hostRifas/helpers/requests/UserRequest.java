package com.host.hostRifas.helpers.requests;

public record UserRequest(

    String username,
    String password,
    String email

) {
    
}
