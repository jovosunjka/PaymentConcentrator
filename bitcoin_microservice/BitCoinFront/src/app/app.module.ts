import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { payService } from './service/payService';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes =[
  { path: "payBitcoin/:transactionId/:amount/:currency", component: HomeComponent },
  {
    path: '', // localhost:4200 redirect to localhost:4200/button
    component: HomeComponent,
    pathMatch: 'full'
  }
]


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    RouterModule.forRoot(routes)
  ],
  providers: [payService],
  bootstrap: [AppComponent]
})
export class AppModule { }
