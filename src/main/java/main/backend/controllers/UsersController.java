package main.backend.controllers;

import jakarta.persistence.EntityNotFoundException;
import main.backend.DTOs.UserResponse;
import main.backend.models.User;
import main.backend.services.JwtService;
import main.backend.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.logging.Logger;

@CrossOrigin
@RestController
public class UsersController {

    private final UsersService usersService;

    private final JwtService jwtService;
    @Autowired
    public UsersController(UsersService usersService, JwtService jwtService) {
        this.usersService = usersService;
        this.jwtService = jwtService;
    }
    @PostMapping(value="/register", produces ="application/json")
    public ResponseEntity<UserResponse> addUser(@RequestBody User user){
        try{
            UserResponse createdUser = usersService.addNewUser(user.getLogin(), user.getPassword());
            String token = jwtService.generateToken(createdUser.getId());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .header("Authorization", "Bearer " +token)
                    .body(createdUser);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
    @PostMapping(value="/login", produces ="application/json")
    public ResponseEntity<UserResponse> attemptLogin(@RequestBody User user){
        try{
            UserResponse foundUser = usersService.attemptLogin(user.getLogin(), user.getPassword());
            String token = jwtService.generateToken(foundUser.getId());
            return ResponseEntity.status(HttpStatus.OK).
                    header("Authorization", "Bearer " +token)
                    .body(foundUser);
        }catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    @GetMapping(value="/login", produces ="application/json")
    public ResponseEntity<String> getUsers(){
        return ResponseEntity.status(HttpStatus.OK).body("Please log in to /login");
    }
}
