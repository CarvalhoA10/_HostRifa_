package com.host.hostRifas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.host.hostRifas.models.raffle.NumberModel;

@Repository
public interface INumberRepository extends JpaRepository<NumberModel, Long>{
    
}
