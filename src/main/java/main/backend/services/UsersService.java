package main.backend.services;

import main.backend.DTOs.UserResponse;

public interface UsersService {
    UserResponse addNewUser(String login, String password) ;
    UserResponse attemptLogin(String login, String password);
}
