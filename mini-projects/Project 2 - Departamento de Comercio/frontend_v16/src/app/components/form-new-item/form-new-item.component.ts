import { Component, Input, Output, EventEmitter, ChangeDetectionStrategy } from '@angular/core';

@Component({
  selector: 'app-form-new-item',
  templateUrl: './form-new-item.component.html',
  styleUrls: ['./form-new-item.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class FormNewItemComponent {
  
  @Input() className ='btn-primary';
  @Input() label!:string;
  @Output() newItemEvent = new EventEmitter<string>();

  onAddNewItem (item:string) {
    console.log("item ->", item);
    this.newItemEvent.emit(item);
  }

  countRender():boolean{
    console.log('Render Form')
    return true;
  }
}
