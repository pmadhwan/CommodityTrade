import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatInputModule, MatFormFieldModule, MatButtonModule, MatSidenavModule, MatIconModule, MatListModule  } from '@angular/material';
import { MatCardModule } from '@angular/material/card';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatRadioModule} from '@angular/material/radio';
import {MatDialogModule} from '@angular/material/dialog';
import {MatTableModule} from '@angular/material/table';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';

@NgModule({
  imports: [
    CommonModule,
    MatButtonModule,
    MatInputModule,
    MatCardModule,
    MatToolbarModule,
    MatProgressBarModule,
    MatSnackBarModule,
    MatRadioModule,
    MatFormFieldModule,
    MatDialogModule,
    MatTableModule,
    MatDatepickerModule,
    MatProgressSpinnerModule
  ],
  exports: [
    MatInputModule,
    MatCardModule,
    MatButtonModule,
    MatToolbarModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatProgressBarModule,
    MatSnackBarModule,
    MatRadioModule,
    MatFormFieldModule,
    MatDialogModule,
    MatTableModule,
    MatDatepickerModule,
    MatProgressSpinnerModule
  ],
  declarations: []
})
export class AppMaterialModule { }
