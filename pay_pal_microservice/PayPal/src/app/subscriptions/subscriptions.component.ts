import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';

declare var paypal;
@Component({
  selector: 'app-subscriptions',
  templateUrl: './subscriptions.component.html',
  styleUrls: ['./subscriptions.component.css']
})
export class SubscriptionsComponent implements OnInit {

  planId: any;  
  subcripId: any;  
  username = 'ASy9frbU07oOFDNmyma5SMKbKKKed4w3HCtEgs3-tBB-aR8OR5Ug1bh3tiQVvIFEi5j0CTWsEXUv41tF';
  password = 'EKWeZcuiWHSuTocRKFer9aforQxmhawEccihGDWxIwuTzD5XmL9_n-l5AwlJRNotaix9HYM4aBI0eXhf';
  basicAuth = 'Bearer A21AAHqmfgvn5C7AWq1oqMjJZsSjzQpg-OMFQwMb9m19UiK70YKBUvrsA9WgwAovMuKh_-fM20M-Sj7kFuD17tMy53pI4T2Og';
  //basicAuth = 'Basic ASy9frbU07oOFDNmyma5SMKbKKKed4w3HCtEgs3-tBB-aR8OR5Ug1bh3tiQVvIFEi5j0CTWsEXUv41tF:EKWeZcuiWHSuTocRKFer9aforQxmhawEccihGDWxIwuTzD5XmL9_n-l5AwlJRNotaix9HYM4aBI0eXhf';
  @ViewChild('paypal', { static: false }) paypalElement: ElementRef;
  constructor() { }

  ngOnInit() {
    // const xhttp = new XMLHttpRequest();  
    // xhttp.onreadystatechange = function () {  
    //   if (this.readyState === 4 && this.status === 201) {  
    //     console.log(JSON.parse(this.responseText));  
    //     alert(JSON.stringify(this.responseText));  
    //   }  
    // };

    //   xhttp.open('POST', 'https://api.sandbox.paypal.com/v1/oauth2/token');  
    //   xhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    //   xhttp.setRequestHeader('Content-Type', "application/json");
    //   xhttp.setRequestHeader('Authorization', this.username + ':' + this.password);  
    //   var params = 'grant_type=client_credentials'
    //   xhttp.send(params);
  }

  getSubcriptionDetails() {  
    const xhttp = new XMLHttpRequest();  
    xhttp.onreadystatechange = function () {  
      if (this.readyState === 4 && this.status === 201) {  
        console.log(JSON.parse(this.responseText));  
        alert(JSON.stringify(this.responseText));  
      } 
    };  

    this.planId = 'P-6KY570575L8166015LYVRHPY';
    xhttp.open('POST', 'https://api.sandbox.paypal.com/v1/billing/subscriptions');  
    xhttp.setRequestHeader('Content-Type', "application/json");
    xhttp.setRequestHeader('Authorization', this.basicAuth);  
    
    xhttp.send(JSON.stringify({ 
      "plan_id": this.planId,
       "start_time": "2020-11-01T00:00:00Z", 
      "shipping_amount": {
        "currency_code": "USD",
        "value": "10.00"
      },
      "subscriber": {
        "name": {
          "given_name": "John",
          "surname": "Doe"
        },
        "email_address": "customer@example.com",
        "shipping_address": {
          "name": {
            "full_name": "John Doe"
          },
          "address": {
            "address_line_1": "2211 N First Street",
            "address_line_2": "Building 17",
            "admin_area_2": "San Jose",
            "admin_area_1": "CA",
            "postal_code": "95131",
            "country_code": "US"
          }
        }
      }
  })); 

  }  

}
