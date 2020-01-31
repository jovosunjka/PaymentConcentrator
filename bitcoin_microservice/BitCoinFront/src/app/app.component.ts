import { Component, HostListener } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'BitCoinFront';

  // @HostListener('window:popstate', ['$event'])
  // onPopState(event) {
  //   console.log('Back button clicked');
  // }

  // @HostListener('window:onbeforeunload', [ '$event' ])
  // beforeUnloadHander(event) {
  //   alert('window:beforeunload');
  // }

  
  // @HostListener('window:unload', [ '$event' ])
  // unloadHandler(event) {
  //   alert('window:beforeunload');
  // }

  // @HostListener('window:beforeunload', [ '$event' ])
  // beforeUnloadHander(event) {
  //   alert('window:beforeunload');
  // }
}

