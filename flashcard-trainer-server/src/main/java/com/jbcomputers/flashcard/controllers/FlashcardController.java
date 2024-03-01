package com.jbcomputers.flashcard.controllers;

import com.jbcomputers.flashcard.entities.Flashcard;
import com.jbcomputers.flashcard.entities.FlashcardSet;
import com.jbcomputers.flashcard.services.FlashcardService;
import com.jbcomputers.flashcard.services.MergeSort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/flashcard")
public class FlashcardController {

    final private FlashcardService flashcardService;
    final private MergeSort mergeSort;

    public FlashcardController(FlashcardService flashcardService, MergeSort mergeSort) {
        this.flashcardService = flashcardService;
        this.mergeSort = mergeSort;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Flashcard>> getAllFlashcards() {
        List<Flashcard> flashcards = flashcardService.getAllFlashcards();
        return new ResponseEntity<>(flashcards, HttpStatus.OK);
    }

    @GetMapping("/sets")
    public ResponseEntity<List<FlashcardSet>> getAllFlashcardSets() {
        List<FlashcardSet> flashcardSets = flashcardService.getFlashcardSets();
        return new ResponseEntity<>(flashcardSets, HttpStatus.OK);
    }

    @GetMapping("/sets/{setId}")
    public ResponseEntity<List<Flashcard>> getFlashcardsForSet(@PathVariable("setId") Long setId) {
        List<Flashcard> flashcards = flashcardService.getFlashcardSet(setId);
        return new ResponseEntity<>(flashcards, HttpStatus.OK);
    }

    @PostMapping("/sets")
    public ResponseEntity<FlashcardSet> createOrUpdateFlashcardSet(@RequestBody FlashcardSet flashcardSet) {
        FlashcardSet set = flashcardService.createOrUpdateFlashcardSet(flashcardSet);
        return new ResponseEntity<>(flashcardSet, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Flashcard> createOrUpdateFlashcard(@RequestBody Flashcard flashcard) {
        Flashcard updatedFlashcard = flashcardService.createOrUpdateFlashcard(flashcard);
        return new ResponseEntity<>(updatedFlashcard, HttpStatus.OK);
    }

    @PostMapping("/sets/delete")
    public ResponseEntity<Boolean> deleteFlashcardSet(@RequestBody Long id) {
        boolean result = flashcardService.deleteFlashcardSet(id);
        return new ResponseEntity<Boolean>(result, HttpStatus.OK);
    }

    @GetMapping("/trigger/mergesort")
    public void triggerMergeSort() {
        mergeSort.run();
    }

}
