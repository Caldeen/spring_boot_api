package main.backend.controllers;

import main.backend.models.Quote;
import main.backend.services.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin
@RequestMapping(value="/quotes")
public class QuotesController {
    private final QuoteService quoteService;


    @Autowired
    public QuotesController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping(value="/random", produces ="application/json")
    public ResponseEntity<Quote> getRandomQuote(){
        return ResponseEntity.status(200).body(quoteService.getRandomQuote());
    }

    @GetMapping(value="/{id}", produces ="application/json")
    public ResponseEntity<Quote> getQuote(@PathVariable Long id){
        return ResponseEntity.status(200).body(quoteService.getQuoteById(id));
    }

    @GetMapping(value="/author/{author}", produces ="application/json")
    public ResponseEntity<List<Quote>> getQuotesByAuthor(@PathVariable String author){
        return ResponseEntity.status(200).body(quoteService.getQuotesByAuthor(author));
    }

    @GetMapping(value="/tags", produces ="application/json")
    public ResponseEntity<String[]> getTags(){
        return ResponseEntity.status(200).body(quoteService.getTags());
    }
}

