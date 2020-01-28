import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ButtonComponent } from './button/button.component';
import { RouterModule, Routes } from '@angular/router';
import { saveTransactionService } from './services/saveTransactionService';
import { HttpClientModule } from '@angular/common/http';
import { SubscriptionsComponent } from './subscriptions/subscriptions.component';
import { ShowCreatedTransactionComponent } from './show-created-transaction/show-created-transaction.component';

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
  },
  {
    path: 'showCreatedTransaction',
    component: ShowCreatedTransactionComponent
  }
]

@NgModule({
  declarations: [
    AppComponent,
    ButtonComponent,
    SubscriptionsComponent,
    ShowCreatedTransactionComponent
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
