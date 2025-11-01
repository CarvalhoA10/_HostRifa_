package com.host.hostRifas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.host.hostRifas.models.user.UserModel;

@Repository
public interface IUserRepository extends JpaRepository<UserModel, Long>{
    
    public UserModel findByUsername(String username);
    public UserModel findByEmail(String email);

}
