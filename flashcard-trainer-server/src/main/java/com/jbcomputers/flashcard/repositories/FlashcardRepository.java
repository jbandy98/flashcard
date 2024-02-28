package com.jbcomputers.flashcard.repositories;

import com.jbcomputers.flashcard.entities.Flashcard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlashcardRepository extends CrudRepository<Flashcard, Long> {

    public List<Flashcard> findAllBySetId(Long setId);
}
