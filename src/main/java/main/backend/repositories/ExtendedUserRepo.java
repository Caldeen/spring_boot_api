package main.backend.repositories;

import main.backend.models.User;

public interface ExtendedUserRepo {
    User findByLogin(String login);
}
