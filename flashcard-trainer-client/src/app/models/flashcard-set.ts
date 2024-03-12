import { FlashCard } from "./flashcard";

export class FlashcardSet {
    id: number = -1;
    setName: string = '';
    updateDate: number | undefined;
    flashcards: Array<FlashCard>;

    // constructor() {
    //     this.flashcards = new Array<FlashCard>();
    // }
}