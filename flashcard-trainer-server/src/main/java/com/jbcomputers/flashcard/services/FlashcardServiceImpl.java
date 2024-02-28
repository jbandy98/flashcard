package com.jbcomputers.flashcard.services;

import com.jbcomputers.flashcard.entities.Flashcard;
import com.jbcomputers.flashcard.entities.FlashcardSet;
import com.jbcomputers.flashcard.exceptions.FlashcardException;
import com.jbcomputers.flashcard.repositories.FlashcardRepository;
import com.jbcomputers.flashcard.repositories.FlashcardSetRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FlashcardServiceImpl implements FlashcardService {

    private final FlashcardRepository flashcardRepository;
    private final FlashcardSetRepository flashcardSetRepository;

    public FlashcardServiceImpl(FlashcardRepository flashcardRepository, FlashcardSetRepository flashcardSetRepository) {
        this.flashcardRepository = flashcardRepository;
        this.flashcardSetRepository = flashcardSetRepository;
    }

    @Override
    public FlashcardSet createNewFlashcardSet(FlashcardSet flashcardSet) {
        FlashcardSet newSet;
        if (flashcardSet.getSetName().isEmpty()) {
            throw new FlashcardException("Flashcard Set Name cannot be null!");
        } else if (flashcardSetRepository.findById(flashcardSet.getId()).isPresent()) {
            throw new FlashcardException("Flashcard Set id already exists!");
        }
        else {
           newSet = flashcardSetRepository.save(flashcardSet);
        }
        return newSet;
    }

    @Override
    public FlashcardSet updateFlashcardSet(FlashcardSet flashcardSet) {
        FlashcardSet updatedSet;
        if(flashcardSetRepository.findById(flashcardSet.getId()).isEmpty()) {
            throw new FlashcardException("Flashcard Set was not found!");
        } else {
            updatedSet = flashcardSetRepository.save(flashcardSet);
        }
        return updatedSet;
    }

    @Override
    public boolean deleteFlashcardSet(Long id) {
        if(flashcardSetRepository.findById(id).isEmpty()) {
            throw new FlashcardException("Flashcard Set was not found!");
        } else {
            flashcardSetRepository.deleteById(id);
        }
        return true;
    }

    @Override
    public Flashcard createNewFlashcard(Flashcard flashcard) {
        Flashcard newFlashcard;
        if (flashcard.getSetId() <= 0 || flashcard.getFrontText().isEmpty() || flashcard.getBackText().isEmpty()) {
            throw new FlashcardException("Either the set id or front or back text was missing from the flashcard!");
        } else if (flashcardSetRepository.findById(flashcard.getSetId()).isEmpty()) {
            throw new FlashcardException("The flashcard set included in the flash card was not found!");
        }
        else {
            newFlashcard = flashcardRepository.save(flashcard);
        }
        return newFlashcard;
    }

    @Override
    public Flashcard updateFlashcard(Flashcard flashcard) {
        Flashcard updatedFlashcard;
        if (flashcardRepository.findById(flashcard.getId()).isEmpty()) {
            throw new FlashcardException("Flashcard id not found!");
        } else if (flashcard.getSetId() <= 0 || flashcard.getFrontText().isEmpty() || flashcard.getBackText().isEmpty()) {
            throw new FlashcardException("Either the set id or front or back text was missing from the flashcard!");
        } else {
            updatedFlashcard = flashcardRepository.save(flashcard);
        }
        return updatedFlashcard;
    }

    @Override
    public boolean deleteFlashcard(Long id) {
        if(flashcardRepository.findById(id).isEmpty()) {
            throw new FlashcardException("Flashcard was not found!");
        } else {
            flashcardRepository.deleteById(id);
        }
        return true;
    }

    @Override
    public List<Flashcard> getFlashcardSet(Long setId) {
        List<Flashcard> setFlashcards = flashcardRepository.findAllBySetId(setId);
        if (setFlashcards.isEmpty()) {
            throw new FlashcardException("No flashcards found for set!");
        }
        return setFlashcards;
    }

    @Override
    public List<FlashcardSet> getFlashcardSets() {
        List<FlashcardSet> flashcardSets = (List<FlashcardSet>) flashcardSetRepository.findAll();
        if (flashcardSets.isEmpty()) {
            throw new FlashcardException("No flash card sets were found.");
        }
        return flashcardSets;
    }

    @Override
    public List<Flashcard> getAllFlashcards() {
        List<Flashcard> flashcards = (List<Flashcard>) flashcardRepository.findAll();
        if (flashcards.isEmpty()) {
            throw new FlashcardException("No flash cards were found...");
        }
        return flashcards;
    }
}
