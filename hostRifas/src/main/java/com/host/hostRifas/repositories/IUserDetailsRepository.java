package com.host.hostRifas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.host.hostRifas.models.user.UserModel;

@Repository
public interface IUserDetailsRepository extends JpaRepository<UserModel, Long>{
    
    UserDetails findByEmail(String email);

}
