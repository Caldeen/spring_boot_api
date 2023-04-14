package main.backend.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import main.backend.models.Quote;

import java.util.Arrays;
import java.util.List;

public class QuoteRepoImpl implements QuoteRepoExtended{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Quote getRandomQuote() {
        return entityManager.createQuery("SELECT q FROM Quote q ORDER BY RANDOM()", Quote.class)
                .setMaxResults(1)
                .getSingleResult();
    }

    @Override
    public List<Quote> getQuotesByAuthor(String author) {
        author = "%" + author + "%";
//        author = author.replace(" ", "%");
        return entityManager.createQuery("SELECT q FROM Quote q WHERE q.author like :author", Quote.class)
                .setParameter("author", author)
                .getResultList();
    }

    @Override
    public String[] getTags() {
        return entityManager.createQuery("SELECT DISTINCT q.tag FROM Quote q", String.class)
                .getResultStream().toArray(String[]::new);
    }
}
