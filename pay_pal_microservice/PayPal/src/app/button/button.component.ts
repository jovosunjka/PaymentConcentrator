import { Component, OnInit, NgZone, HostListener } from '@angular/core';
import { saveTransactionService } from '../services/saveTransactionService';
import { Transaction } from '../model/Transaction';
import { ActivatedRoute, Router } from '@angular/router';

declare let paypal: any;

@Component({
  selector: 'app-button',
  templateUrl: './button.component.html',
  styleUrls: ['./button.component.css']
})
export class ButtonComponent implements OnInit {

  obavljjenaTransakcija: boolean;
  private transactionId: number;
  private amount: number;
  private currency: string;
  transaction: Transaction;
  private sendedTransaction: boolean;
  private payedWithPayPal: boolean;
  constructor(private serviceSave: saveTransactionService, private route: ActivatedRoute, private routerRedirect: Router, private ngZone: NgZone) { }

  ngOnInit() {
    if (this.route.snapshot.params.transactionId) {
      this.transactionId = this.route.snapshot.params.transactionId;
      this.amount = this.route.snapshot.params.amount;
      this.currency = this.route.snapshot.params.currency;
    }
    this.sendedTransaction = false;
    this.payedWithPayPal = false;
  }

  private loadExternalScript(scriptUrl: string) {
    return new Promise((resolve, reject) => {
      const scriptElement = document.createElement('script')
      scriptElement.src = scriptUrl
      scriptElement.onload = resolve
      document.body.appendChild(scriptElement)
    })
  }

  ngAfterViewInit(): void {
    var c = this.amount; //cena u dolarima
    var v = this.serviceSave;
    var router = this.routerRedirect;
    var self = this;
    if(this.currency == "RSD"){
      c = Math.floor(c/106);
    }else if(this.currency == "EUR"){
      c = Math.round(c * 1.1);
    }
    
    var transactionLocal = this.transactionId;
    var zone = this.ngZone;
    this.loadExternalScript("https://www.paypalobjects.com/api/checkout.js").then(() => {
      paypal.Button.render({
        env: 'sandbox',
        client: {
          production: 'sb-4cj1l802098@business.example.com',//'marko_srb-facilitator@hotmail.rs',//'sb-6cjxj802030@business.example.com',
          sandbox: 'ASy9frbU07oOFDNmyma5SMKbKKKed4w3HCtEgs3-tBB-aR8OR5Ug1bh3tiQVvIFEi5j0CTWsEXUv41tF'//'AaKKhbw0-y_k74YKRjnPUQwzuqZepCRbQKpy3KHmRh-EFYm5TCNBkUl0naEscvskKOAfaKTLIjxFWD_T' //'AaIWMz997XX6dWiUaize8IB6sEn9KEp0HeZlw03gJUCQqK97hzo3VlgYILpZ71cYurHA1gqumQZqwcSa'
        },
        commit: true,
        payment: function (data, actions) {
          return actions.payment.create({
            payment: {
              transactions: [
                {
                  amount: { total: c, currency: 'USD' }
                }
              ]
            }

          })
        },
        onAuthorize: function (data, actions) {
          console.log("Izvrsenje placanja");
          return actions.payment.execute().then(function (payment) {
            console.log("prosledjeno na upis u bazu");
            console.log(payment);
            self.sendedTransaction = true;
            // TODO
            // cart: "3VG18081MV5040101"
            // create_time: "2019-08-08T15:51:37Z"
            // id: "PAYID-LVGEKCQ1NP00636Y1192210E"
            self.payedWithPayPal = true;
            let t = new Transaction();
            t.cart = payment.cart;
            t.create_time = payment.create_time;
            t.id = payment.id;
            t.idPayment = transactionLocal;
            t.state = "SUCCESS";
            v.save(t).subscribe((response) =>{
              console.log(response);
              zone.runOutsideAngular(() => {
                window.location.href = response.redirectUrl;
              });
            });
          })
        },
        onCancel: function (data) {
          console.log("Cancel Transaction");
          self.sendedTransaction = true;
          let t = new Transaction();
            t.idPayment = transactionLocal;
            t.cart = "none";
            t.create_time = "none";
            t.id = data.paymentID;
            t.state = "FAIL";
            if(self.payedWithPayPal){
              v.save(t).subscribe((response) =>{
                console.log(response);
                zone.runOutsideAngular(() => {
                  window.location.href = response.redirectUrl;
                  
                });
              });
            }
        },
        onError: function (data) {
          console.log("Error transaction"); 
          self.sendedTransaction = true;
          let t = new Transaction();
            t.idPayment = transactionLocal;
            t.cart = "none";
            t.create_time = "none";
            t.id = data.paymentID;
            t.state = "FAIL";
            if(self.payedWithPayPal){
              v.save(t).subscribe((response) =>{
                console.log(response);
                zone.runOutsideAngular(() => {
                  window.location.href = response.redirectUrl;
                  
                });
              });
            }
        }
      }, '#paypal-button');
    });
    this.sendedTransaction = self.sendedTransaction;
    this.payedWithPayPal = self.payedWithPayPal;
    //alert(this.payedWithPayPal);
  }


  all(){
    this.serviceSave.allTransaction().subscribe();
  }

  subscribeComponent(){
    this.routerRedirect.navigate(['subscription/:transactionID='+ this.transactionId]);
  }

  @HostListener('window:beforeunload', [ '$event' ])
  beforeUnloadHander(event) {
    console.log(JSON.stringify(event));
    if(!this.sendedTransaction){
      console.log(this.sendedTransaction);
      this.serviceSave.cancelTransaction(this.transactionId).subscribe();
    }
    //alert('window:beforeunload');
  }
  // @HostListener('window:unload', [ '$event' ])
  // unloadHandler(event) {
  //   alert("ugasen tab")
  //   let t = new Transaction();
  //           t.idPayment = this.transactionId;
  //           t.cart = "none";
  //           t.create_time = "none";
  //           t.id = new Int32Array(0);
  //           t.state = "FAIL";
  //   //this.authenticationService.logout();

  //   this.serviceSave.cancel(t).subscribe();
  // }

  // @HostListener('window:beforeunload', [ '$event' ])
  // beforeUnloadHander(event) {
  //   alert('window:beforeunload');
  // }
  
}
