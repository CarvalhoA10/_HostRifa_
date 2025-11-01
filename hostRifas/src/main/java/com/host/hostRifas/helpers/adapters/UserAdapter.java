package com.host.hostRifas.helpers.adapters;

import org.springframework.beans.BeanUtils;

import com.host.hostRifas.helpers.requests.UserRequest;
import com.host.hostRifas.helpers.responses.UserResponse;
import com.host.hostRifas.models.user.UserModel;

public class UserAdapter {
    
    public static UserResponse toUserResponse(UserModel model){
        UserResponse response = new UserResponse();
        BeanUtils.copyProperties(model, response);
        return response;
    }

    public static UserResponse toUserResponse(UserRequest request){
        UserResponse response = new UserResponse();
        BeanUtils.copyProperties(request, response);
        return response;
    }

    public static UserModel toUserModel(UserRequest userRequest){
        UserModel model = new UserModel();
        BeanUtils.copyProperties(userRequest, model);
        return model;
    }

}
