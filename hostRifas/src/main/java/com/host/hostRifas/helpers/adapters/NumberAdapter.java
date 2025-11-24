package com.host.hostRifas.helpers.adapters;

import org.apache.commons.beanutils.BeanUtils;

import com.host.hostRifas.helpers.responses.NumberResponse;
import com.host.hostRifas.models.raffle.NumberModel;

public class NumberAdapter {
    
    public static NumberResponse toResponse(NumberModel model) throws Exception{
        NumberResponse response = new NumberResponse();
        BeanUtils.copyProperties(model, response);
        return response;
    }


}
