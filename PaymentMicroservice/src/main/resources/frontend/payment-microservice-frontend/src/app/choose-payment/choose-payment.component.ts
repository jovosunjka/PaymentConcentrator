import { Component, OnInit } from '@angular/core';
import {PaymentType} from "../model/payment-type";
import {HttpService} from "../services/http/http.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-choose-payment',
  templateUrl: './choose-payment.component.html',
  styleUrls: ['./choose-payment.component.css']
})
export class ChoosePaymentComponent implements OnInit {

  paymentTypes: PaymentType[];

  private relativeUrlForPaymentTypes = "/payment/types"

  constructor(private httpService: HttpService, private toastr: ToastrService) {
    this.paymentTypes = [];
  }

  ngOnInit() {
    this.getPaymentTypes();
  }

  getPaymentTypes() {
    this.httpService.getAll(this.relativeUrlForPaymentTypes)
      .subscribe(
        (paymentTypes: PaymentType[]) => {
          this.paymentTypes = paymentTypes;
          this.toastr.success('The payment types have been successfully delivered!');
        },
        (err) => {
          this.paymentTypes = [];
          this.toastr.error('The payment types have not been successfully delivered!');
        }
      );
  }

}
