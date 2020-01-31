import { Component, OnInit, ElementRef, ViewChild, NgZone, Input } from '@angular/core';
import { saveTransactionService } from '../services/saveTransactionService';
import { Transaction } from '../model/Transaction';

declare var paypal;
@Component({
  selector: 'app-subscriptions',
  templateUrl: './subscriptions.component.html',
  styleUrls: ['./subscriptions.component.css']
})
export class SubscriptionsComponent implements OnInit {

  @Input() transactionID: number;
  @Input() amount: number;
  @Input() currency: string;
  planId: any;  
  subcripId: any;  
  username = 'ASy9frbU07oOFDNmyma5SMKbKKKed4w3HCtEgs3-tBB-aR8OR5Ug1bh3tiQVvIFEi5j0CTWsEXUv41tF';
  password = 'EKWeZcuiWHSuTocRKFer9aforQxmhawEccihGDWxIwuTzD5XmL9_n-l5AwlJRNotaix9HYM4aBI0eXhf';
  basicAuth = 'Bearer A21AAGqCWCurh91hRoF8KRCB5g4Iva8DeKJvrJf8Ta7VZny2H0zOqY-FNZEbqi21j7lp8qKIbSkVQcD6Sa1DjXLHE-2fgZyJA';
  //basicAuth = 'Basic ASy9frbU07oOFDNmyma5SMKbKKKed4w3HCtEgs3-tBB-aR8OR5Ug1bh3tiQVvIFEi5j0CTWsEXUv41tF:EKWeZcuiWHSuTocRKFer9aforQxmhawEccihGDWxIwuTzD5XmL9_n-l5AwlJRNotaix9HYM4aBI0eXhf';
  @ViewChild(SubscriptionsComponent, {static: false}) paypalElement: ElementRef;
  constructor(private service: saveTransactionService, private ngZone: NgZone) { }

  // ngOnInit() {
  // }

  // getSubcriptionDetails() {  
  //   const xhttp = new XMLHttpRequest();  
  //   xhttp.onreadystatechange = function () {  
  //     if (this.readyState === 4 && this.status === 201) {  
  //       console.log(JSON.parse(this.responseText));  
  //       alert(JSON.stringify(this.responseText));  
  //     } 
  //   };  

  //   this.planId = 'P-6KY570575L8166015LYVRHPY';
  //   xhttp.open('POST', 'https://api.sandbox.paypal.com/v1/billing/subscriptions');  
  //   xhttp.setRequestHeader('Content-Type', "application/json");
  //   xhttp.setRequestHeader('Authorization', this.basicAuth);  
    
  //   xhttp.send(JSON.stringify({ 
  //     "plan_id": this.planId,
  //      "start_time": "2020-11-01T00:00:00Z", 
  //     "shipping_amount": {
  //       "currency_code": "USD",
  //       "value": "10.00"
  //     },
  //     "subscriber": {
  //       "name": {
  //         "given_name": "John",
  //         "surname": "Doe"
  //       },
  //       "email_address": "customer@example.com",
  //       "shipping_address": {
  //         "name": {
  //           "full_name": "John Doe"
  //         },
  //         "address": {
  //           "address_line_1": "2211 N First Street",
  //           "address_line_2": "Building 17",
  //           "admin_area_2": "San Jose",
  //           "admin_area_1": "CA",
  //           "postal_code": "95131",
  //           "country_code": "US"
  //         }
  //       }
  //     }
  // })); 

  // } 

  ngOnInit() {   
    //pokusaj dobavljanja novog tokena
    this.service.getSubscribeToken().subscribe((response) =>{
      //console.log(response.access_token);
      this.basicAuth = "Bearer " + response.access_token;
    })

    var c = this.amount;  
    if(this.currency == "RSD"){
      c = Math.floor(c/106);
    }else if(this.currency == "EUR"){
      c = Math.round(c * 1.1);
    }
    console.log("Cena pretplate " + c + this.currency);
    
    const self = this;  
    this.planId = 'P-6KY570575L8166015LYVRHPY';  //Default Plan Id
    //this.loadExternalScript("https://www.paypal.com/sdk/js?client-id=ASy9frbU07oOFDNmyma5SMKbKKKed4w3HCtEgs3-tBB-aR8OR5Ug1bh3tiQVvIFEi5j0CTWsEXUv41tF&vault=true").then(() => {
    paypal.Buttons({  
      createSubscription: function (data, actions) {  
        return actions.subscription.create({  
          'plan_id': self.planId, 
          "shipping_amount": {
                  "currency_code": "USD",
                  "value": c
                }, 
        });  
      },  
      onApprove: function (data, actions) {  
        console.log(data);  
        alert('You have successfully created subscription ' + data.subscriptionID);  
        self.getSubcriptionDetails(data.subscriptionID);  
        let t = new Transaction();
            t.cart = "none";
            t.create_time = Date.now().toString();
            t.id = self.transactionID.toString();
            t.idPayment = self.transactionID;
            t.state = "SUBSCRIBE_COMPLETE";
            self.service.save(t).subscribe((response) =>{
              console.log(response);
              self.ngZone.runOutsideAngular(() => {
                window.location.href = response.redirectUrl;
              });
            });
      },  
      onCancel: function (data) {  
        // Show a cancel page, or return to cart  
        let t = new Transaction();
            t.cart = "none";
            t.create_time = Date.now().toString();
            t.id = self.transactionID.toString();
            t.idPayment = self.transactionID;
            t.state = "SUBSCRIBE_FAILE";
            self.service.save(t).subscribe((response) =>{
              console.log(response);
              self.ngZone.runOutsideAngular(() => {
                window.location.href = response.redirectUrl;
              });
            });
        console.log(data);  
      },  
      onError: function (err) {  
        // Show an error page here, when an error occurs 
        let t = new Transaction();
            t.cart = "none";
            t.create_time = Date.now().toString();
            t.id = self.transactionID.toString();
            t.idPayment = self.transactionID;
            t.state = "SUBSCRIBE_FAILE";
            self.service.save(t).subscribe((response) =>{
              console.log(response);
              self.ngZone.runOutsideAngular(() => {
                window.location.href = response.redirectUrl;
              });
            }); 
        console.log(err);  
      }  
  
    }).render('#paypal-subscribe-button');  
    //});
  }

  getSubcriptionDetails(subcriptionId) {  
    const xhttp = new XMLHttpRequest();  
    xhttp.onreadystatechange = function () {  
      if (this.readyState === 4 && this.status === 200) {  
        console.log(JSON.parse(this.responseText));  
        alert(JSON.stringify(this.responseText));  
      }  
    };  
    xhttp.open('GET', 'https://api.sandbox.paypal.com/v1/billing/subscriptions/' + subcriptionId, true);  
    xhttp.setRequestHeader('Authorization', this.basicAuth);  
  
    xhttp.send();  
  }

}
