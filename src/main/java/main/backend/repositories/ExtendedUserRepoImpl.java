package main.backend.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import main.backend.models.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.logging.Logger;

public class ExtendedUserRepoImpl implements ExtendedUserRepo{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public User findByLogin(String login) {

        User foundUser = null;
        try{
            foundUser = entityManager.createQuery("SELECT u FROM User u WHERE u.login = :login", User.class)
                    .setParameter("login", login)
                    .getSingleResult();
            return foundUser;
        }
        catch (Exception e){
            Logger.getGlobal().info(e.toString());
            return null;
        }
    }
}
