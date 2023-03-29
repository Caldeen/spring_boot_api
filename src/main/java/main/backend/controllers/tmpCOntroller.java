package main.backend.controllers;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import main.backend.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController()
public class tmpCOntroller {

    private final JwtService jwtService;
    @Autowired
    public tmpCOntroller(JwtService jwtService) {
        this.jwtService = jwtService;
    }
    @GetMapping("/test")
    public ResponseEntity<String> test(@RequestHeader("Authorization") String token){
        token = token.substring(7);
        try{
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(jwtService.isValidToken(token) ? "Valid" : "Invalid");
        }catch (MalformedJwtException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Malformed jwt");
        }catch (ExpiredJwtException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Expired jwt");
        }
    }
}
