package main.backend.services;

import main.backend.models.Quote;
import main.backend.repositories.QuoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class QuoteServiceImpl implements QuoteService{
    private final QuoteRepo quoteRepo;
    @Autowired
    public QuoteServiceImpl(QuoteRepo quoteRepo) {
        this.quoteRepo = quoteRepo;
    }

    @Override
    public Quote getQuoteById(Long id) {
        return quoteRepo.findById(id).orElse(null);
    }

    @Override
    public String[] getTags() {
        return quoteRepo.getTags();
    }
    @Override
    public Quote getRandomQuote() {
        return quoteRepo.getRandomQuote();
    }

    @Override
    public List<Quote> getQuotesByAuthor(String author) {
        return quoteRepo.getQuotesByAuthor(author);
    }

}
