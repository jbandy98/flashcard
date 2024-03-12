import { Component, Input, OnInit } from '@angular/core';
import { FlashCard } from '../models/flashcard';
import { FlashcardService } from '../services/src/app/flashcardservice.service';
import { ToastrService } from 'ngx-toastr';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-study',
  templateUrl: './study.component.html',
  styleUrls: ['./study.component.scss']
})
export class StudyComponent implements OnInit{
  
  @Input()
  flashcards: Array<FlashCard>;
  setId: number;
  currentIndex = 0;
  front: boolean = true;

  constructor(private flashcardService: FlashcardService,
    private alertService: ToastrService,
    private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.flashcards = new Array<FlashCard>();
    this.setId = Number(this.route.snapshot.paramMap.get('setId'));
    console.log("Set id passed in is " + this.setId);
    this.loadFlashcardSet();
  }

  loadFlashcardSet() {
    this.flashcardService.getFlashcardsForSet(this.setId).subscribe((data) => {
      this.flashcards = data;
    },
    error => {
      this.alertService.error("Having issues pulling the set of flashcards up....");
    });
  }

  showCardFront(): string {
    return this.flashcards[this.currentIndex].frontText;
  }

  showCardBack(): string {
    return this.flashcards[this.currentIndex].backText;
  }

  showBack() {
    this.front = false;
  }

  showFront() {
    this.front = true;
  }

  goBack() {
    this.currentIndex--;
    if (this.currentIndex < 0) {
      this.currentIndex = this.flashcards.length - 1;
    }
    this.front = true;
  }

  goForward() {
    this.currentIndex++;
    if (this.currentIndex >= this.flashcards.length) {
      this.currentIndex = 0;
    }
    this.front = true;
  }
}
