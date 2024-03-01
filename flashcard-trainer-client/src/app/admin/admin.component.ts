import { Component, OnInit, ViewChild } from '@angular/core';
import {CdkTextareaAutosize} from '@angular/cdk/text-field';
import { FlashcardService } from '../services/src/app/flashcardservice.service';
import { FlashcardSet } from '../models/flashcard-set';
import { ToastrService } from 'ngx-toastr';
import { FlashCard } from '../models/flashcard';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit {
  setNameInput: string = '';
  flashcardSetResponse: FlashcardSet;

  displayedColumns1: string[] = ['setName','actions'];
  displayedColumns2: string[] = ['frontText','backText'];
  flashcardSetData: Array<FlashcardSet>;

  selectedSet: FlashcardSet;
  flashcards: Array<FlashCard>;
  flashcardResponse: FlashCard;
  showFlashcards: boolean = false;
  frontText: string = '';
  backText: string = '';

  // @ViewChild('autosize') autosize: CdkTextareaAutosize;

  constructor(private flashcardService: FlashcardService,
    private alertService: ToastrService) {
    this.flashcardSetResponse = new FlashcardSet();
    this.flashcardSetData = new Array<FlashcardSet>();
    this.selectedSet = new FlashcardSet();
    this.flashcards = new Array<FlashCard>();
    this.flashcardResponse = new FlashCard();
  }

  ngOnInit() {
    this.pullSetData();
  }

  pullSetData() {
    this.flashcardService.getFlashcardSets().subscribe((data) => {
      this.flashcardSetData = data;
      console.log('Flashcard Set Data retrieved.');
    });
  }

  deleteSet(id: number) {
    this.flashcardService.deleteFlashcardSet(id).subscribe((response) => {
      if (response == true) {
        this.alertService.success("Flashcard Set successfully deleted.");
      } else {
        this.alertService.warning("Error when trying to delete flashcard set.");
      }
      this.pullSetData();
    });
  }

  createNewSet() {
    if (this.setNameInput != '') {
      let flashcardSet = new FlashcardSet();
      flashcardSet.setName = this.setNameInput;
      
      this.flashcardService.createOrUpdateSet(flashcardSet).subscribe((data) => {
        this.flashcardSetResponse = data;
        this.alertService.success("New Flashcard Set has been created named " + this.flashcardSetResponse.setName + " with id " + this.flashcardSetResponse.id + " at " + this.flashcardSetResponse.updateDate);
      });
    }
  }

  loadFlashcardSet(flashcardSet: FlashcardSet) {
    this.selectedSet = flashcardSet;
    this.pullFlashcardsForSet(flashcardSet.id);
    this.showFlashcards = true;
  }

  pullFlashcardsForSet(setId: number) {
    this.flashcardService.getFlashcardsForSet(setId).subscribe((data) => {
      this.flashcards = data;
      console.log('flashcard data pulled.');
    });
  }

  createNewFlashcard() {
    let flashcard = new FlashCard();
    flashcard.frontText = this.frontText;
    flashcard.backText = this.backText;
    flashcard.setId = this.selectedSet.id;
    this.flashcardService.createOrUpdateFlashcard(flashcard).subscribe((data) => {
      this.flashcardResponse = data;
      this.alertService.success("New flashcard created!");
      this.pullFlashcardsForSet(this.selectedSet.id);
    });
  }

}
