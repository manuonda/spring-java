import { Component, Input, OnChanges, OnDestroy, OnInit, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-app-button',
  templateUrl: './app-button.component.html',
  styleUrls: ['./app-button.component.scss']
})
export class AppButtonComponent {
  @Input() color!:string;
  @Input() label!:string;

  // ngOnInit(): void {
  //   console.log("ngOnInit");
  // }
  // ngOnChanges(changes: SimpleChanges): void {
  //   console.log("ngOnChanges", changes);
  // }
  // ngOnDestroy(): void {
  //   console.log("ngOnDestroy");
  // }

  counterRender():boolean{
    console.log("Render Button");
    return true;
  }

 
}
