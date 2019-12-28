import { Component, OnInit } from '@angular/core';
import { saveTransactionService } from '../services/saveTransactionService';
import { Transaction } from '../model/Transaction';

declare let paypal: any;

@Component({
  selector: 'app-button',
  templateUrl: './button.component.html',
  styleUrls: ['./button.component.css']
})
export class ButtonComponent implements OnInit {

  constructor(private serviceSave: saveTransactionService) { }

  ngOnInit() {
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
            
            v.save(t).subscribe();
            console.log(payment);
          })
        }
      }, '#paypal-button');
    });
  }


  all(){
    this.serviceSave.allTransaction().subscribe();
  }
}
