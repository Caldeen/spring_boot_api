package main.backend.repositories;

import main.backend.models.User;

import java.util.Optional;

public interface ExtendedUserRepo {
    Optional<User> findByLogin(String login);
}
