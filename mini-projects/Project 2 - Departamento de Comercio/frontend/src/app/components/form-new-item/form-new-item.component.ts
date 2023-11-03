import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-form-new-item',
  templateUrl: './form-new-item.component.html',
  styleUrls: ['./form-new-item.component.scss']
})
export class FormNewItemComponent {
  
  @Input() className ='btn-primary';
  @Input() label!:string;
  @Output() newItemEvent = new EventEmitter<string>();

  onAddNewItem (item:string) {
    console.log("item ->", item);
    this.newItemEvent.emit(item);
  }
}
