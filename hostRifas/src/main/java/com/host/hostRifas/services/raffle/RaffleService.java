package com.host.hostRifas.services.raffle;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.host.hostRifas.helpers.adapters.NumberAdapter;
import com.host.hostRifas.helpers.adapters.RaffleAdapter;
import com.host.hostRifas.helpers.raffle.NumberStatus;
import com.host.hostRifas.helpers.raffle.RaffleStatus;
import com.host.hostRifas.helpers.requests.RaffleRequest;
import com.host.hostRifas.helpers.responses.NumberResponse;
import com.host.hostRifas.helpers.responses.RaffleResponse;
import com.host.hostRifas.models.raffle.NumberModel;
import com.host.hostRifas.models.raffle.RaffleModel;
import com.host.hostRifas.models.raffle.WinnerModel;
import com.host.hostRifas.models.user.UserModel;
import com.host.hostRifas.repositories.INumberRepository;
import com.host.hostRifas.repositories.IRaffleRepository;
import com.host.hostRifas.repositories.IUserRepository;
import com.host.hostRifas.repositories.IWinnerRepository;

@Service
public class RaffleService {
    
    private IRaffleRepository iRaffleRepository;
    private INumberRepository iNumberRepository;
    private IUserRepository iUserRepository;
    private IWinnerRepository iWinnerRepository;

    public RaffleService(IRaffleRepository iRaffleRepository, INumberRepository iNumberRepository, IUserRepository iUserRepository, IWinnerRepository iWinnerRepository){
        this.iRaffleRepository = iRaffleRepository;
        this.iNumberRepository = iNumberRepository;
        this.iUserRepository = iUserRepository;
        this.iWinnerRepository = iWinnerRepository;
    }

    public RaffleResponse getById(Long id){

        try{
            RaffleModel raffle = this.iRaffleRepository.findById(id).get();
            RaffleResponse response = RaffleAdapter.toResponse(raffle);

            List<NumberModel> numbers = this.iNumberRepository.findByRaffle(raffle);
            List<NumberResponse> numberResponses = new ArrayList<>();
            
            for(NumberModel number : numbers){

                NumberResponse n = NumberAdapter.toResponse(number);
                numberResponses.add(n);

            }

            response.setNumbers(numberResponses);
            return response;

        }catch(Exception ex){

            return new RaffleResponse();

        }

        

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
            iNumberRepository.save(number);
        }

        return RaffleAdapter.toResponse(model);
    }

    public RaffleResponse raffleValidate(Long id, RaffleStatus status){
        return new RaffleResponse();
    }


    public boolean raffleRealize(Long userId, Long raffleId){

        UserModel user = this.iUserRepository.findById(userId).get();

        RaffleModel raffle = this.iRaffleRepository.findById(raffleId).get();
        raffle.setStatus(RaffleStatus.realized);
        Random rand = new Random();
        Long numberRelized = rand.nextLong(raffle.getNumbers().size()) + 1;

        NumberModel numberModel = new NumberModel();

        for(NumberModel number : raffle.getNumbers()){
            if(number.getId() == numberRelized){
                number.setStatus(NumberStatus.drawn);
                numberModel = number;
            }
        }

        WinnerModel winner = new WinnerModel();
        winner.setNumber(numberModel);
        winner.setRaffle(raffle);
        winner.setUser(user);
        winner.setRealizatedAt(LocalDateTime.now());

        iWinnerRepository.save(winner);

        return true;
    }


}
