package com.host.hostRifas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.host.hostRifas.models.raffle.WinnerModel;
import com.host.hostRifas.models.user.UserModel;

@Repository
public interface IWinnerRepository extends JpaRepository<WinnerModel, Long>{
    
    WinnerModel findByUser(UserModel user);

}
