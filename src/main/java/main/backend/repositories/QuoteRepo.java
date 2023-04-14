package main.backend.repositories;

import main.backend.models.Quote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepo extends CrudRepository<Quote, Long> {
}
