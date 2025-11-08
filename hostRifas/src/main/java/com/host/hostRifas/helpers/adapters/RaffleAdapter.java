package com.host.hostRifas.helpers.adapters;

import org.springframework.beans.BeanUtils;

import com.host.hostRifas.helpers.requests.RaffleRequest;
import com.host.hostRifas.helpers.responses.RaffleResponse;
import com.host.hostRifas.models.raffle.RaffleModel;

public class RaffleAdapter {

    public static RaffleModel toModel(RaffleRequest request){
        RaffleModel model = new RaffleModel();
        BeanUtils.copyProperties(request, model);
        return model;
    }
    
    public static RaffleResponse toResponse(RaffleRequest request){
        RaffleResponse response = new RaffleResponse();
        BeanUtils.copyProperties(request, response);
        return response;
    }

    public static RaffleResponse toResponse(RaffleModel model){
        RaffleResponse response = new RaffleResponse();
        BeanUtils.copyProperties(model, response);
        return response;
    }


}
