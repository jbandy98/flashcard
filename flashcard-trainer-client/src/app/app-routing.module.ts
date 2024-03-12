import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FlashcardSetComponent } from './flashcard-set/flashcard-set.component';
import { AdminComponent } from './admin/admin.component';
import { HomeComponent } from './home/home.component';
import { StudyComponent } from './study/study.component';

const routes: Routes = [
  { path: 'sets', component: FlashcardSetComponent },
  { path: 'admin', component: AdminComponent },
  { path: 'study/:setId', component: StudyComponent },
  { path: '**', component: HomeComponent }
]

@NgModule({
  imports: [
    RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
