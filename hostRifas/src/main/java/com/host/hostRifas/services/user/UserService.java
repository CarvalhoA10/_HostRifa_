package com.host.hostRifas.services.user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.host.hostRifas.helpers.adapters.UserAdapter;
import com.host.hostRifas.helpers.requests.UserRequest;
import com.host.hostRifas.helpers.responses.UserResponse;
import com.host.hostRifas.helpers.user.UserRole;
import com.host.hostRifas.models.user.UserModel;
import com.host.hostRifas.repositories.IUserRepository;

@Service
public class UserService {
    
    private IUserRepository iUserRepository;

    public UserService(IUserRepository iUserRepository){
        this.iUserRepository = iUserRepository;
    }

    public UserResponse getById(Long id){
        return UserAdapter.toUserResponse(this.iUserRepository.findById(id).get());
    }

    public UserResponse getByUsername(String username){
        return UserAdapter.toUserResponse(this.iUserRepository.findByUsername(username));
    }

    public UserResponse getByEmail(String email){
        return UserAdapter.toUserResponse(this.iUserRepository.findByEmail(email));
    }

    public List<UserResponse> getAll(){
        
        List<UserModel> users = this.iUserRepository.findAll();

        List<UserResponse> responses = new ArrayList<>();

        for(UserModel model : users){
            responses.add(UserAdapter.toUserResponse(model));
        }

        return responses;

    }

    public boolean checkIfExistsByEmail(String email){
        UserResponse check = this.getByEmail(email);

        if(check != null){
            return true;
        }

        return false;
    }

    public boolean checkIfExistsByUsername(String username){
        UserResponse check = this.getByUsername(username);

        if(check != null){
            return true;
        }

        return false;
    }

    public UserResponse create(UserRequest request){

        UserResponse response = new UserResponse();

        if(this.checkIfExistsByEmail(request.email())){
            response.error.add("Usuário com este email já existe");
        }

        if(this.checkIfExistsByUsername(request.username())){
            response.error.add("Usuário com este username já existe");
        }

        if(response.error.size() > 0){
            return response;
        }
        
        UserModel model = UserAdapter.toUserModel(request);

        model.setActive(false);
        model.setRole(UserRole.user);
        model.setPassword(new BCryptPasswordEncoder().encode(model.getPassword()));
        model.setCreatedAt(LocalDateTime.now());

        response = UserAdapter.toUserResponse(this.iUserRepository.save(model));

        return response;

    }

    public boolean delete(Long id){
        UserModel user = this.iUserRepository.findById(id).get();
        this.iUserRepository.delete(user);
        return true;
    }
    
}
