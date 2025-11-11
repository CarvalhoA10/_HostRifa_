package com.host.hostRifas.services.raffle;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.host.hostRifas.helpers.adapters.RaffleAdapter;
import com.host.hostRifas.helpers.raffle.NumberStatus;
import com.host.hostRifas.helpers.raffle.RaffleStatus;
import com.host.hostRifas.helpers.requests.RaffleRequest;
import com.host.hostRifas.helpers.responses.RaffleResponse;
import com.host.hostRifas.models.raffle.NumberModel;
import com.host.hostRifas.models.raffle.RaffleModel;
import com.host.hostRifas.repositories.INumberRepository;
import com.host.hostRifas.repositories.IRaffleRepository;
import com.host.hostRifas.repositories.IUserRepository;
import com.host.hostRifas.services.user.UserService;

@Service
public class RaffleService {
    
    private IRaffleRepository iRaffleRepository;
    private INumberRepository iNumberRepository;
    private IUserRepository iUserRepository;

    public RaffleService(IRaffleRepository iRaffleRepository, INumberRepository iNumberRepository, IUserRepository iUserRepository){
        this.iRaffleRepository = iRaffleRepository;
        this.iNumberRepository = iNumberRepository;
        this.iUserRepository = iUserRepository;
    }

    public List<RaffleResponse> allRaffle(){
        List<RaffleModel> models = this.iRaffleRepository.findAll();
        List<RaffleResponse> responses = new ArrayList<>();
        for(RaffleModel model : models){
            responses.add(RaffleAdapter.toResponse(model));
        }

        return responses;
    }

    public RaffleResponse insertRaffle(RaffleRequest request, String username, String pathRaffle, String pathPrizze){
        RaffleModel model = RaffleAdapter.toModel(request);
        model.setRaffleImage(pathRaffle);
        model.setPrizeImage(pathPrizze);
        model.setCreatedAt(LocalDateTime.now());
        model.setStatus(RaffleStatus.analyzing);
        model.setUser(this.iUserRepository.findByUsername(username));
        model = iRaffleRepository.save(model);

        for(int i = 0; i < request.getQtdNumbers(); i++){
            NumberModel number = new NumberModel();
            number.setNumber(i+1);
            number.setCreatedAt(LocalDateTime.now());
            number.setRaffle(model);
            number.setStatus(NumberStatus.available);
        }

        return RaffleAdapter.toResponse(model);
    }

}
