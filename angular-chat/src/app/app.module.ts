import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {HttpClientModule} from '@angular/common/http';
import {MessagesComponent} from './messages/messages.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {RouterModule} from '@angular/router';
import {MatDialogModule} from '@angular/material/dialog';
import {PickerModule} from "@ctrl/ngx-emoji-mart";

@NgModule({
  declarations: [
    AppComponent,
    MessagesComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    FormsModule,
    RouterModule,
    MatDialogModule,
    PickerModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
