import { Component, OnInit } from '@angular/core';
import { payService } from '../service/payService';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private http: payService) { }

  ngOnInit() {
  }

  pay(){
    this.http.pay().subscribe();
  }
}
