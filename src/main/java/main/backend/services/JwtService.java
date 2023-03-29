package main.backend.services;

public interface JwtService {
    String generateToken(String login);
    String getLoginFromToken(String token);
    boolean isValidToken(String token);
    String to_string(String token);
}
