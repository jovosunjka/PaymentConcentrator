import { Component, OnInit, NgZone } from '@angular/core';
import { payService } from '../service/payService';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  private transactionId: number;
  
  constructor(private http: payService, private route: ActivatedRoute, private ngZone: NgZone) { }

  ngOnInit() {
    if (this.route.snapshot.params.transactionId) {
      this.transactionId = this.route.snapshot.params.transactionId;
      console.log("Broj transakcije stigle sa bekenda: " + this.transactionId);
    }
  }

  pay(){
    this.http.pay().subscribe((linijesabekenda)=>{
      //this.linijeZaView = linijesabekenda;
      this.ngZone.runOutsideAngular(() => {
        window.location.href = linijesabekenda.redirectUrl;
      });
      err => console.log(err);
    }
    );
  }
}
