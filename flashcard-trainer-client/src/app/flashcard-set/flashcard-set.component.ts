import { Component, OnInit } from '@angular/core';
import { FlashcardService } from '../services/src/app/flashcardservice.service';
import { FlashcardSet } from '../models/flashcard-set';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-flashcard-set',
  templateUrl: './flashcard-set.component.html',
  styleUrls: ['./flashcard-set.component.scss']
})
export class FlashcardSetComponent implements OnInit{
  displayedColumns: string[] = ['setId', 'setName', 'cardCount', 'updateDate', 'studySet'];
  flashcardSetData: Array<FlashcardSet>;

  constructor(private flashcardService: FlashcardService,
    private alertService: ToastrService) {
    this.flashcardSetData = new Array<FlashcardSet>();
   }

  ngOnInit(): void {
    this.pullSetData();
  }

  pullSetData() {
    this.flashcardService.getFlashcardSets().subscribe((data) => {
      this.flashcardSetData = data;
      console.log('Flashcard Set Data retrieved.');
    });
  }

  studySet(set: FlashcardSet) {

  }
}
