import { Component, OnInit, ɵINJECTOR_IMPL__POST_R3__, Input } from '@angular/core';
import { Transaction } from '../model/Transaction';

@Component({
  selector: 'app-show-created-transaction',
  templateUrl: './show-created-transaction.component.html',
  styleUrls: ['./show-created-transaction.component.css']
})
export class ShowCreatedTransactionComponent implements OnInit {

  @Input() transaction: Transaction;  
  id1: Int32Array;
  cart: string;
  create_time: string;
  state: string;

  constructor() { }

  ngOnInit() {
    this.id1 = this.transaction.id;
  }
}
