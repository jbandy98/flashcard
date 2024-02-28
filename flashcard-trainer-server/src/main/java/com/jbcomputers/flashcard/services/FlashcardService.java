package com.jbcomputers.flashcard.services;

import com.jbcomputers.flashcard.entities.Flashcard;
import com.jbcomputers.flashcard.entities.FlashcardSet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FlashcardService {

    public FlashcardSet createNewFlashcardSet(FlashcardSet flashcardSet);

    public FlashcardSet updateFlashcardSet(FlashcardSet flashcardSet);

    public boolean deleteFlashcardSet(Long id);

    public Flashcard createNewFlashcard(Flashcard flashcard);

    public Flashcard updateFlashcard(Flashcard flashcard);

    public boolean deleteFlashcard(Long id);

    public List<Flashcard> getFlashcardSet(Long setId);

    public List<FlashcardSet> getFlashcardSets();

    public List<Flashcard> getAllFlashcards();
}
