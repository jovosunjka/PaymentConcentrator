import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { AuthHttpService } from './services/auth-service';
import { RegistrationComponent } from './registration/registration';
import { RouterModule, Routes } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';

const routes: Routes =[
  {path: "" ,component: RegistrationComponent},
  {path: "registration" ,component: RegistrationComponent},
  {path: "**" , redirectTo:"registration"}
]

@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule,
    RouterModule.forRoot(routes)
  ],
  providers: [AuthHttpService],
  bootstrap: [AppComponent]
})
export class AppModule { }
