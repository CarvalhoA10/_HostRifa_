package com.host.hostRifas.services.raffle;

import org.springframework.stereotype.Service;

import com.host.hostRifas.repositories.IRaffleRepository;

@Service
public class RaffleService {
    
    private IRaffleRepository iRaffleRepository;

    public RaffleService(IRaffleRepository iRaffleRepository){
        this.iRaffleRepository = iRaffleRepository;
    }

    

}
