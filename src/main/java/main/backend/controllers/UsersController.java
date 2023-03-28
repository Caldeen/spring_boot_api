package main.backend.controllers;

import lombok.val;
import main.backend.models.User;
import main.backend.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.PasswordAuthentication;
import java.util.List;
import java.util.logging.Logger;

@RestController
@CrossOrigin
public class UsersController {
    @Autowired
    private UsersService usersService;

    @PostMapping(value="/users", produces ="application/json")
    public ResponseEntity<User> addUser(@RequestBody User user){
        try{
            User createdUser = usersService.addNewUser(user.getLogin(), user.getPassword());
            Logger.getGlobal().info(createdUser.getLogin() + " " + createdUser.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(user);
        }
    }
    @GetMapping(value="/users", produces ="application/json")
    public ResponseEntity<List<User>> getUsers(){

        return ResponseEntity.status(HttpStatus.OK).body(usersService.getAllUsers());
    }
}
