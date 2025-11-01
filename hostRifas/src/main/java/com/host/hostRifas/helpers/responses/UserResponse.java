package com.host.hostRifas.helpers.responses;

import java.util.ArrayList;
import java.util.List;

import com.host.hostRifas.helpers.user.UserRole;

public class UserResponse {
    
    private Long id;
    public List<String> error;
    private String username;
    private String email;
    private boolean isActive;
    private UserRole role;

    public UserResponse(){
        this.error = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
    
    
}
