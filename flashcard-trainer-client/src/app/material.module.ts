import { NgModule } from "@angular/core";
import { MatMenuModule } from "@angular/material/menu";
import { MatButtonModule } from '@angular/material/button';
import { MatDividerModule } from '@angular/material/divider';

@NgModule({
    imports: [
        MatMenuModule,
        MatButtonModule,
        MatDividerModule
    ],
    exports: [
        MatMenuModule,
        MatButtonModule,
        MatDividerModule
    ]
  })
  export class MaterialModule { }