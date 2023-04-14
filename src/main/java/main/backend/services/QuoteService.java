package main.backend.services;

import main.backend.models.Quote;

import java.util.List;

public interface QuoteService {
    Quote getQuoteById(Long id);
    List<Quote> getAllQuotes();
}
