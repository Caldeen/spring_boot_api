package main.backend.services;

import main.backend.models.User;

import java.lang.reflect.Array;
import java.util.List;

public interface UsersService {
    User addNewUser(String login, String password) ;
     List<User> getAllUsers();
}
