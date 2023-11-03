import { Component, OnChanges, OnDestroy, OnInit, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent  {
  title = 'frontend';
  cities:Array<string>;
  selection: string;
  
  constructor(){
    this.cities = ['Lima','citi2','Lima','Barcelona'];
    this.selection = "";
  }

  onCityClicked(city:string) {
     console.log("onCityClicked", city);
     this.selection = city;
  }

 
  onClear():void {
    this.selection ="";
  }
  addnewCity(city:string) {
    this.cities.push(city);
  }
}
