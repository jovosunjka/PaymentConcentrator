import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ChoosePaymentComponent } from './choose-payment/choose-payment.component';
import {HttpClientModule} from "@angular/common/http";
import {ToastrModule} from "ngx-toastr";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";

@NgModule({
  declarations: [
    AppComponent,
    ChoosePaymentComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    ToastrModule.forRoot({preventDuplicates: true}), // ToastrModule added
  ],
  providers: [
    { provide: 'BASE_API_URL', useValue: 'https://localhost:8083' }, //useValue: 'https://localhost:8083/api' },  // environment.apiUrl
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
