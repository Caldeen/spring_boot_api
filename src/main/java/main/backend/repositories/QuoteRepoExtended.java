package main.backend.repositories;

import main.backend.models.Quote;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepoExtended {
    Quote getRandomQuote();
    List<Quote> getQuotesByAuthor(String author);

    String[] getTags();
}
