import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ButtonComponent } from './button/button.component';
import { RouterModule, Routes } from '@angular/router';
import { saveTransactionService } from './services/saveTransactionService';
import { HttpClientModule } from '@angular/common/http';
import { SubscriptionsComponent } from './subscriptions/subscriptions.component';

const routes: Routes = [
  { path: 'button/:transactionId', component: ButtonComponent },
  {
      path: '', // localhost:4200 redirect to localhost:4200/button
      redirectTo: '/button',
      pathMatch: 'full'
  },
  {
    path: 'subscription',
    component: SubscriptionsComponent
  }
]

@NgModule({
  declarations: [
    AppComponent,
    ButtonComponent,
    SubscriptionsComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    RouterModule.forRoot(routes)
  ],
  providers: [saveTransactionService],
  bootstrap: [AppComponent]
})
export class AppModule { }
