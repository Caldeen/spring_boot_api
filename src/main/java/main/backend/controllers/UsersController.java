package main.backend.controllers;

import jakarta.persistence.EntityNotFoundException;
import main.backend.models.User;
import main.backend.services.JwtService;
import main.backend.services.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.logging.Logger;

@RestController
@CrossOrigin(exposedHeaders = "Authorization")
public class UsersController {

    private final UsersService usersService;

    private final JwtService jwtService;

    public UsersController(UsersService usersService, JwtService jwtService) {
        this.usersService = usersService;
        this.jwtService = jwtService;
    }
    @PostMapping(value="/register", produces ="application/json")
    public ResponseEntity<User> addUser(@RequestBody User user){
        try{
            User createdUser = usersService.addNewUser(user.getLogin(), user.getPassword());
            Logger.getGlobal().info(createdUser.getLogin() + " " + createdUser.getPassword());
            String token = jwtService.generateToken(createdUser.getId());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .header("Authorization", "Bearer " +token)
                    .body(createdUser);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(user);
        }
    }
    @PostMapping(value="/login", produces ="application/json")
    public ResponseEntity<User> attemptLogin(@RequestBody User user){
        try{
            User foundUser = usersService.attemptLogin(user.getLogin(), user.getPassword());
            String token = jwtService.generateToken(foundUser.getId());
            Logger.getGlobal().info("Token: " + token);
            Logger.getGlobal().info(jwtService.to_string(token));
            return ResponseEntity.status(HttpStatus.OK).
                    header("Authorization", "Bearer " +token)
                    .build();
        }catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(user);
        }
    }
    @GetMapping(value="/login", produces ="application/json")
    public ResponseEntity<String> getUsers(){
        return ResponseEntity.status(HttpStatus.OK).body("Please log in to /login");
    }
}
