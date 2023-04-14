package main.backend.services;

import main.backend.models.Quote;
import main.backend.repositories.QuoteRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class QuoteServiceImpl implements QuoteService{
    private final QuoteRepo quoteRepo;

    public QuoteServiceImpl(QuoteRepo quoteRepo) {
        this.quoteRepo = quoteRepo;
    }

    @Override
    public Quote getQuoteById(Long id) {
        return quoteRepo.findById(id).orElse(null);
    }

    @Override
    public List<Quote> getAllQuotes() {
        return StreamSupport.stream(quoteRepo.findAll().spliterator(), false)
                .toList();
    }
}
