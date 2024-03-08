import { Component, Input  } from '@angular/core';

@Component({
  selector: 'app-details',
  standalone: true,
  imports: [],
  templateUrl: './details.component.html',
  styleUrl: './details.component.scss'
})
export  default class DetailsComponent {


   @Input({alias:'id'}) productId!: number;
}
