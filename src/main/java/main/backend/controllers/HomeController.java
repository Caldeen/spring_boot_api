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


@RestController()
public class HomeController {

    private final JwtService jwtService;
    @Autowired
    public HomeController(JwtService jwtService) {
        this.jwtService = jwtService;
    }
    @GetMapping("/")
    public ResponseEntity<String> index(){
        return ResponseEntity.status(HttpStatus.OK).body("""
                register at /register with json body:
                {
                    "login": "your_login",
                    "password": "your_password"
                }
                login at /login with json body:
                {
                    "login": "your_login",
                    "password": "your_password"
                }
                """);
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
