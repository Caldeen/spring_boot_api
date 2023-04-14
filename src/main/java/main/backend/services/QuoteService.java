package main.backend.services;

import main.backend.models.Quote;

import java.util.List;

public interface QuoteService {
    Quote getQuoteById(Long id);

    String[] getTags();

    Quote getRandomQuote();

    List<Quote> getQuotesByAuthor(String author);

}
