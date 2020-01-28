import { Component, OnInit } from '@angular/core';
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

  private transactionId: number;
  transaction: Transaction;
  constructor(private serviceSave: saveTransactionService, private route: ActivatedRoute, private routerRedirect: Router) { }

  ngOnInit() {
    if (this.route.snapshot.params.transactionId) {
      this.transactionId = this.route.snapshot.params.transactionId;
    }

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
    var c = 50; //cena u dolarima
    var v = this.serviceSave;
    var router = this.routerRedirect;
    
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
                  amount: { total: 50500, currency: 'USD' }
                }
              ]
            }

          })
        },
        onAuthorize: function (data, actions) {
          console.log("Izvrsenje placanja");
          return actions.payment.execute().then(function (payment) {
            // TODO
            // cart: "3VG18081MV5040101"
            // create_time: "2019-08-08T15:51:37Z"
            // id: "PAYID-LVGEKCQ1NP00636Y1192210E"
            let t = new Transaction();
            console.log("prosledjeno na upis u bazu");
            t.cart = payment.cart;
            t.create_time = payment.create_time;
            t.id = payment.id;
            t.state = payment.state;
            v.save(t).subscribe();
            console.log(payment);
            //router.navigate(['showCreatedTransaction', {transaction: t}]);
          })
        },
        onCancel: function (data) {
          console.log("Cancel Transaction");
          
          let t = new Transaction();
            t.cart = "none";
            t.create_time = "none";
            t.id = data.paymentID;
            t.state = "Invalid";
            v.cancel(t).subscribe();
        },
        onError: function (data) {
          console.log("Error transaction"); 
          let t = new Transaction();
            t.cart = "none";
            t.create_time = "none";
            t.id = data.paymentID;
            t.state = "Invalid";
            v.cancel(t).subscribe();
        }
      }, '#paypal-button');
    });
    
  }


  all(){
    this.serviceSave.allTransaction().subscribe();
  }

  
}
