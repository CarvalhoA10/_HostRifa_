package com.host.hostRifas.services.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class prizeScheduled {
    
    @Scheduled(fixedDelay = 600000)
    @Transactional
    public void draw(){

    }

}
