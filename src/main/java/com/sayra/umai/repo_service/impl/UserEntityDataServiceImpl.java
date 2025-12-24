package com.sayra.umai.repo_service.impl;

import com.sayra.umai.model.entity.user.UserEntity;
import com.sayra.umai.repo.UserEntityRepo;
import com.sayra.umai.repo_service.UserEntityDataService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserEntityDataServiceImpl implements UserEntityDataService {
    private final UserEntityRepo userEntityRepo;

    public UserEntityDataServiceImpl(UserEntityRepo userEntityRepo){
        this.userEntityRepo=userEntityRepo;
    }

    @Override
    public UserEntity findByUsernameOrThrow(String username) {
        if(!isValidUsername(username)) throw new IllegalArgumentException("Invalid username: " + username);
        return userEntityRepo.findByUsername(username).orElseThrow(
                ()-> new UsernameNotFoundException("User with username: " + username + " not found"));
    }

    @Override
    public boolean existsByRoles_NameOrThrow(String name) {
        if(name.isEmpty()){
            throw new IllegalArgumentException("Name: " + name + " is invalid");
        }
        return userEntityRepo.existsByRoles_Name(name);
    }

    @Override
    public boolean existsByUsernameOrThrow(String username) {
        if(!isValidUsername(username)){
            throw new IllegalArgumentException("User with username: " + username + " not found");
        }
        return userEntityRepo.existsByUsername(username);
    }

    @Override
    public boolean existsByEmailOrThrow(String email) {
        if(isValidEmail(email)){
            return userEntityRepo.existsByEmail(email);
        }else{
            throw new IllegalArgumentException("Email: " + email + " is invalid");
        }
    }

    private boolean isValidUsername(String username){
        if(username.isEmpty()) return false;
        return true;
    }
    private boolean isValidEmail(String email){
        if(email.isEmpty()) return false;
        return true;

    }
}
