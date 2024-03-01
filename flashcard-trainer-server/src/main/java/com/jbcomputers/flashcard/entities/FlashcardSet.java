package com.jbcomputers.flashcard.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Data
@Entity(name="flashcard_sets")
public class FlashcardSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="set_name")
    private String setName;

    @Column(name="update_date")
    @UpdateTimestamp
    private LocalDateTime updateDate;
}
