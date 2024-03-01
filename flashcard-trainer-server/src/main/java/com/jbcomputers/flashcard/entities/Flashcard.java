package com.jbcomputers.flashcard.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Data
@Entity(name="flashcards")
public class Flashcard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="set_id")
    private Long setId;

    @Column(name="front_text")
    private String frontText;

    @Column(name="back_text")
    private String backText;

    @Column(name="update_date")
    @UpdateTimestamp
    private LocalDateTime updateDate;
}
