import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import {ChoosePaymentComponent} from "./choose-payment/choose-payment.component";


const routes: Routes = [
  { path: 'choose-payment', component: ChoosePaymentComponent },
  {
      path: '', // localhost:4200 redirect to localhost:4200/payment
      redirectTo: '/choose-payment',
      pathMatch: 'full'
  },
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
