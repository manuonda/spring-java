import { Component, EventEmitter, Input, OnInit, Output, ChangeDetectionStrategy } from '@angular/core';

@Component({
  selector: 'app-cities',
  templateUrl: './cities.component.html',
  styleUrls: ['./cities.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class CitiesComponent {
   
   @Input() selection!:string;
   @Input() city!:string;
   @Output() cityClickedEvent = new EventEmitter<string>();

   

   onCityClicked(city:string) {
     console.log("cityClickedEvent: ", city);
     // emite al padre el valor city of type string
     this.cityClickedEvent.emit(city);   
   }

   counterRender():boolean {
    console.log("counterRender Cities");
    return true;
   }
}
