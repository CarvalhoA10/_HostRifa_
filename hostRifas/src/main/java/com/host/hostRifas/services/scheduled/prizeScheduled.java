package com.host.hostRifas.services.scheduled;

import com.host.hostRifas.helpers.raffle.NumberStatus;
import com.host.hostRifas.helpers.raffle.RaffleStatus;
import com.host.hostRifas.models.raffle.NumberModel;
import com.host.hostRifas.models.raffle.RaffleModel;
import com.host.hostRifas.repositories.IRaffleRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class prizeScheduled {

    private IRaffleRepository iRaffleRepository;

    public prizeScheduled(IRaffleRepository iRaffleRepository){
        this.iRaffleRepository = iRaffleRepository;
    }
    
    @Scheduled(fixedDelay = 600000)
    @Transactional
    public void draw(){

        List<RaffleModel> raffles = this.iRaffleRepository.findAll();

        LocalDateTime now = LocalDateTime.now();

        for(RaffleModel raffle : raffles){
            if(now.isAfter(raffle.getPrizeDate()) && raffle.getStatus() == RaffleStatus.approved){
                int size = raffle.getNumbers().size();

                int prize = ThreadLocalRandom.current().nextInt(0, size+1);

                for(NumberModel number : raffle.getNumbers()){
                    if(number.getNumber() == prize){
                        number.setStatus(NumberStatus.drawn);
                        break;
                    }
                }

                raffle.setStatus(RaffleStatus.realized);

                break;
            }
        }

    }

}
