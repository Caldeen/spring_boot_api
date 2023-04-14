package main.backend.controllers;

import main.backend.models.Quote;
import main.backend.services.QuoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(exposedHeaders = "Authorization")
public class QuotesController {
    private final QuoteService quoteService;

    public QuotesController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping(value="/quotes/random", produces ="application/json")
    public ResponseEntity<Quote> getRandomQuote(){
        return ResponseEntity.status(200).body(quoteService.getRandomQuote());
    }
    @GetMapping(value="/quotes/{id}", produces ="application/json")
    public ResponseEntity<Quote> getQuote(@PathVariable Long id){
        return ResponseEntity.status(200).body(quoteService.getQuoteById(id));
    }
    @GetMapping(value="/quotes/author/{author}", produces ="application/json")
    public ResponseEntity<List<Quote>> getQuotesByAuthor(@PathVariable String author){
        return ResponseEntity.status(200).body(quoteService.getQuotesByAuthor(author));
    }
    @GetMapping(value="/quotes/tags", produces ="application/json")
    public ResponseEntity<String[]> getTags(){
        return ResponseEntity.status(200).body(quoteService.getTags());
    }
}
