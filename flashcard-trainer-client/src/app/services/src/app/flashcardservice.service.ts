import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { FlashcardSet } from 'src/app/models/flashcard-set';
import { FlashCard } from 'src/app/models/flashcard';


@Injectable({
  providedIn: 'root'
})
export class FlashcardService {

  serverUrl = 'http://localhost:8080';
  constructor(private httpClient: HttpClient) { }

  getFlashcardSets(): Observable<Array<FlashcardSet>> {
    return this.httpClient.get<Array<FlashcardSet>>(this.serverUrl + '/flashcard/sets');
  }

  createOrUpdateSet(set: FlashcardSet): Observable<FlashcardSet> {
    return this.httpClient.post<FlashcardSet>(this.serverUrl+'/flashcard/sets', set);
  }

  createOrUpdateFlashcard(flashcard: FlashCard): Observable<FlashCard> {
    return this.httpClient.post<FlashCard>(this.serverUrl + '/flashcard', flashcard);
  }

  deleteFlashcardSet(id: number): Observable<boolean> {
    return this.httpClient.post<boolean>(this.serverUrl + '/flashcard/sets/delete', id);
  }

  getFlashcardsForSet(setId: number): Observable<Array<FlashCard>> {
    return this.httpClient.get<Array<FlashCard>>(this.serverUrl + '/flashcard/sets/' + setId);
  }
}
