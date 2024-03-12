package com.jbcomputers.flashcard.repositories;

import com.jbcomputers.flashcard.entities.FlashcardSet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlashcardSetRepository extends CrudRepository<FlashcardSet, Long> {

}
