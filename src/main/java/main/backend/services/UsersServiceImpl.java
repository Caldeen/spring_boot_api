package main.backend.services;

import jakarta.persistence.EntityExistsException;
import main.backend.models.User;
import main.backend.repositories.ExtendedUserRepo;
import main.backend.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public User addNewUser(String login, String password) {
        User foundUser = userRepo.findByLogin(login);
        if (foundUser != null)
            throw new EntityExistsException("User with login " + login + " already exists");
        return userRepo.save(new User(login, passwordEncoder.encode(password)));
    }

    @Override
    public List<User> getAllUsers() {

        return userRepo.findAll();
    }
}

