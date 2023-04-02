package main.backend.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class JwtServiceImpl implements JwtService {
    private final Environment env;
    @Autowired
    public JwtServiceImpl(Environment env) {
        this.env = env;
    }
    @Override
    public String generateToken(Long id) {
        return Jwts.builder()
                .setSubject(id.toString())
                .claim("role", "user")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(Objects.requireNonNull(env.getProperty("jwtExpiration")))))
                .signWith(SignatureAlgorithm.HS512, env.getProperty("jwtSecret"))
                .compact();
    }
    @Override
    public Long getIdFromToken(String token) {
        return Long.parseLong(Jwts.parser()
                .setSigningKey(env.getProperty("jwtSecret"))
                .parseClaimsJws(token)
                .getBody()
                .getSubject());
    }

    @Override
    public boolean isValidToken(String token) {
        if(!Jwts.parser()
                .setSigningKey(env.getProperty("jwtSecret"))
                .isSigned(token)){
            return false;
        }
        return Jwts.parser()
                .setSigningKey(env.getProperty("jwtSecret"))
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .after(new Date());
    }

    @Override
    public String to_string(String token){
        return new StringBuilder()
                .append(token)
                .append(" ")
                .append(Jwts.parser().setSigningKey(env.getProperty("jwtSecret")).parseClaimsJws(token).getBody().getSubject())
                .append(" ")
                .append(Jwts.parser().setSigningKey(env.getProperty("jwtSecret")).parseClaimsJws(token).getBody().getExpiration())
                .append(" ")
                .append(Jwts.parser().setSigningKey(env.getProperty("jwtSecret")).parseClaimsJws(token).getBody().getIssuedAt())
                .append(" ")
                .append(Jwts.parser().setSigningKey(env.getProperty("jwtSecret")).isSigned(token))
                .toString();
    }
}
