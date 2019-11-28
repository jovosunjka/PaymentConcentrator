import { Component, OnInit } from '@angular/core';

declare let paypal: any;

@Component({
  selector: 'app-button',
  templateUrl: './button.component.html',
  styleUrls: ['./button.component.css']
})
export class ButtonComponent implements OnInit {

  constructor() { }

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
    //var v = this.http;
    this.loadExternalScript("https://www.paypalobjects.com/api/checkout.js").then(() => {
      paypal.Button.render({
        env: 'sandbox',
        client: {
          production: 'marko_srb-facilitator@hotmail.rs',
          sandbox: 'AaKKhbw0-y_k74YKRjnPUQwzuqZepCRbQKpy3KHmRh-EFYm5TCNBkUl0naEscvskKOAfaKTLIjxFWD_T'
        },
        commit: true,
        payment: function (data, actions) {
          return actions.payment.create({
            payment: {
              transactions: [
                {
                  amount: { total: 15, currency: 'USD' }
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
            //var l = v.SacuvajTransakciju(payment.id).subscribe();
            console.log(payment);
          })
        }
      }, '#paypal-button');
    });
  }

}
