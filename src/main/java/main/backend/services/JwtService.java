package main.backend.services;

public interface JwtService {
    String generateToken(Long id);
    Long getIdFromToken(String token);
    boolean isValidToken(String token);
    String to_string(String token);
}
