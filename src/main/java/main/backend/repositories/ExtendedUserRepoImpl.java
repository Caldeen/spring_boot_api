package main.backend.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import main.backend.models.User;

import java.util.Optional;
import java.util.logging.Logger;

public class ExtendedUserRepoImpl implements ExtendedUserRepo{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Optional<User> findByLogin(String login) {

        try{
            User foundUser = entityManager.createQuery("SELECT u FROM User u WHERE u.login = :login", User.class)
                    .setParameter("login", login)
                    .getSingleResult();
            return Optional.ofNullable(foundUser);
        }
        catch (Exception e){
            Logger.getGlobal().info(e.toString());
            return Optional.empty();
        }
    }
}
