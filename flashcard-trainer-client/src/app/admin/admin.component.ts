import { Component, OnInit } from '@angular/core';
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
  displayedColumns2: string[] = ['frontText','backText', 'deleteFlashcard'];
  flashcardSetData: Array<FlashcardSet>;
  flashcards: Array<FlashCard>;

  selectedSet: FlashcardSet;
  flashcardResponse: FlashCard;
  showFlashcards: boolean = false;
  frontText: string = '';
  backText: string = '';

  constructor(private flashcardService: FlashcardService,
    private alertService: ToastrService) {
    this.flashcardSetResponse = new FlashcardSet();
    this.flashcardSetData = new Array<FlashcardSet>();
    this.flashcards = new Array<FlashCard>();
    this.selectedSet = new FlashcardSet();
    this.flashcardResponse = new FlashCard();
  }

  ngOnInit() {
    this.pullSetData();
  }

  pullSetData() {
    this.flashcardService.getFlashcardSets().subscribe((data) => {
      this.flashcardSetData = data;
      if (this.selectedSet.id > 0) {
        const updatedSelectedSet = this.flashcardSetData.filter(set => set.id == this.selectedSet.id);
        this.flashcards = updatedSelectedSet[0].flashcards;
        console.log('Flashcards retrieved for set id: ' + this.selectedSet.id);
      }
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
    this.pullSetData();
    this.selectedSet = flashcardSet;
    this.showFlashcards = true;
    
  }

  createNewFlashcard() {
    let flashcard = new FlashCard();
    flashcard.frontText = this.frontText;
    flashcard.backText = this.backText;
    flashcard.setId = this.selectedSet.id;
    this.flashcardService.createOrUpdateFlashcard(flashcard).subscribe((data) => {
      this.flashcardResponse = data;
      this.alertService.success("New flashcard created!");
      this.loadFlashcardSet(this.selectedSet);
      this.frontText = '';
      this.backText = '';
    });
  }

  deleteFlashcard(card: FlashCard) {
    this.flashcardService.deleteFlashcard(card).subscribe((response) => {
      if (response == true) {
        this.alertService.success("Flashcard successfully deleted.");
      }
      this.loadFlashcardSet(this.selectedSet);
    },
      error => {
        this.alertService.error("Flashcard deletion failed!");
    });
  }

}
