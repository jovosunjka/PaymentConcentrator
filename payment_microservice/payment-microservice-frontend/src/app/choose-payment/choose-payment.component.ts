import { Component, OnInit, NgZone } from '@angular/core';
import {PaymentType} from '../model/payment-type';
import {HttpService} from '../services/http/http.service';
import {ToastrService} from 'ngx-toastr';
import { ActivatedRoute } from '@angular/router';
import { RedirectUrlDto } from '../model/redirect-url-dto';

@Component({
  selector: 'app-choose-payment',
  templateUrl: './choose-payment.component.html',
  styleUrls: ['./choose-payment.component.css']
})
export class ChoosePaymentComponent implements OnInit {

  paymentTypes: PaymentType[];
  chosenPaymentType: string;
  private transactionId: number;

  private relativeUrlForPaymentTypes = '/payment/types';
  private relativeUrlChooseForPayment = '/payment/choose-payment';


  constructor(private httpService: HttpService, private route: ActivatedRoute, private ngZone: NgZone, private toastr: ToastrService) {
    this.paymentTypes = [];
  }

  ngOnInit() {
    if (this.route.snapshot.params.transactionId) {
      this.transactionId = this.route.snapshot.params.transactionId;
    }

    this.getPaymentTypes();
  }

  getPaymentTypes() {
    this.httpService.getAll(this.relativeUrlForPaymentTypes)
      .subscribe(
        (paymentTypes: PaymentType[]) => {
          this.paymentTypes = paymentTypes;
          if (this.paymentTypes && this.paymentTypes.length > 0) {
              this.chosenPaymentType = this.paymentTypes[0].name;
          }
          this.toastr.success('The payment types have been successfully delivered!');
        },
        (err) => {
          this.paymentTypes = [];
          this.toastr.error('The payment types have not been successfully delivered!');
        }
      );
  }

  choosePayment() {
    const url = this.relativeUrlChooseForPayment + '?transactionId=' + this.transactionId + '&paymentType=' + this.chosenPaymentType;
    this.httpService.get<RedirectUrlDto>(url).subscribe(
      (redirectUrlDto: RedirectUrlDto) => {
        this.ngZone.runOutsideAngular(() => {
          window.location.href = redirectUrlDto.redirectUrl;
        });

        this.toastr.success('Redirection to KP!');
      },
      (err) => {
        this.toastr.error('Problem with payment!');
        alert(JSON.stringify(err));
      }
    );
  }

}
