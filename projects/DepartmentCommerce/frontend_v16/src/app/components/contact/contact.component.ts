import { Component } from '@angular/core';

interface ContactForm {
  "firstname":string,
  "checkAdult": boolean,
  "department": string,
  "comment": string
}

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.scss']
})
export class ContactComponent {
  
  model!: ContactForm;

  onSubmit(form: any) {
    console.log("Conctact Form : ", form)
    this.model={
      firstname:"informacion",
      checkAdult:true,
      comment:"Cometnario numero 1",
      department:"deparment"
    }
  }
}
