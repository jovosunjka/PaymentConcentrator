import { Component, OnInit, NgZone } from '@angular/core';
import { payService } from '../service/payService';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  transactionId: number;
  paymentID: number;
  private amount: number;
  private currency: string;

  constructor(private http: payService, private ngZone: NgZone, private route: ActivatedRoute) { }

  ngOnInit() {
    if (this.route.snapshot.params.transactionId) {
      this.paymentID = this.route.snapshot.params.transactionId;
      this.amount = this.route.snapshot.params.amount;
      this.currency = this.route.snapshot.params.currency;
    }
    console.log(this.amount + this.currency);
  }

  pay(){
    var c = Math.floor(this.amount);
    this.http.pay(this.currency, c).subscribe((redirectUrlDto) => {
      //console.log(redirectUrlDto.idTransaction);
      this.ngZone.runOutsideAngular(() => {
        //window.location.href = redirectUrlDto.redirectUrl;
        window.open(redirectUrlDto.redirectUrl);
        this.transactionId = redirectUrlDto.idTransaction;
      });
    });
  }

  payComplite(){
    this.http.checkPay(this.paymentID, this.transactionId).subscribe((response) =>{
      window.location.href = response.redirectUrl;
    });
  }
}
