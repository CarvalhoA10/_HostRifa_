package com.host.hostRifas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.host.hostRifas.models.raffle.NumberModel;
import com.host.hostRifas.models.raffle.RaffleModel;

@Repository
public interface INumberRepository extends JpaRepository<NumberModel, Long>{
    
    List<NumberModel> findByRaffle(RaffleModel raffle);

}
