import { Component, OnInit, NgZone } from '@angular/core';
import { payService } from '../service/payService';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  transactionId: number;

  constructor(private http: payService, private ngZone: NgZone) { }

  ngOnInit() {
  }

  pay(){
    this.http.pay().subscribe((redirectUrlDto) => {
      //console.log(redirectUrlDto.idTransaction);
      this.ngZone.runOutsideAngular(() => {
        //window.location.href = redirectUrlDto.redirectUrl;
        window.open(redirectUrlDto.redirectUrl);
        this.transactionId = redirectUrlDto.idTransaction;
      });
    });
  }

  payComplite(){
    this.http.checkPay(this.transactionId).subscribe((response) =>{
      window.location.href = "https://localhost:4200/magazines-page";
    });
  }
}
