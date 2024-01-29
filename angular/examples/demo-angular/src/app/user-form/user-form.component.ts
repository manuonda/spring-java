import { Component } from '@angular/core';

@Component({
  selector: 'app-user-form',
  standalone: true,
  imports: [],
  templateUrl: './user-form.component.html',
  styleUrl: './user-form.component.scss'
})
export class UserFormComponent {
     inputMessage = "Ingresa tus nombres";
     disabledButton= false;

     input(event: Event) : void{
      const inputEvent = event as InputEvent;
      console.log('evento input : ', event);
     }

     clickSaveData() : void{
      console.log('guardando datos...');
    }
}
