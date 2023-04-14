package main.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
public class Quote {
    @Id
    private Long id;
    @Column
    private String quote;
    @Column
    private String author;
    @Column
    private String tag;
}
