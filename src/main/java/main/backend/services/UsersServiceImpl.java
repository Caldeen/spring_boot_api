package main.backend.services;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import main.backend.DTOs.UserResponse;
import main.backend.models.User;
import main.backend.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponse addNewUser(String login, String password) {
        Optional<User> foundUser = userRepo.findByLogin(login);
        if (foundUser.isPresent())
            throw new EntityExistsException("User with login " + login + " already exists");
        return toUserDto(userRepo.save(new User(login, passwordEncoder.encode(password))));
    }

    @Override
    public UserResponse attemptLogin(String login, String password){
        Optional<User> foundUser = userRepo.findByLogin(login);
        if (foundUser.isEmpty())
            throw new EntityNotFoundException("User with login " + login + " not found");
        if (passwordEncoder.matches(password, foundUser.get().getPassword())){
            return toUserDto(foundUser.get());
        }
        throw new EntityNotFoundException("Wrong password");
    }
    private UserResponse toUserDto(User user){
        return UserResponse.builder()
                .id(user.getId())
                .login(user.getLogin())
                .build();
    }
}

