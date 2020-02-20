import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppComponent } from './app.component';
import { AuthHttpService } from './services/auth-service';
import { RegistrationComponent } from './registration/registration';
import { RouterModule, Routes } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { ToastrModule } from 'ngx-toastr';

const routes: Routes =[
   	{path: "registration/:transactionId" ,component: RegistrationComponent},
   	{
      path: '', // localhost:4200 redirect to localhost:4200/registration
      redirectTo: '/registration',
      pathMatch: 'full'
  	}
]

@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule,
    RouterModule.forRoot(routes),
    ToastrModule.forRoot({preventDuplicates: true}), // ToastrModule added,
  ],
  providers: [AuthHttpService],
  bootstrap: [AppComponent]
})
export class AppModule { }
