package com.jbcomputers.flashcard.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Data
@Entity(name="flashcard_sets")
public class FlashcardSet {

    @Id
    private Long id;

    @Column(name="set_name")
    private String setName;

    @Column(name="update_date")
    @UpdateTimestamp
    private LocalDateTime updateDate;
}
