import { CurrencyPipe } from '@angular/common';
import { Component, EventEmitter, Output, input } from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import { RouterLink } from '@angular/router';
import { Product } from '@shared/models/product.interface';

@Component({
  selector: 'app-card',
  standalone: true,
  imports: [CurrencyPipe, MatCardModule,MatButtonModule, RouterLink],
  templateUrl: './card.component.html',
  styleUrl: './card.component.scss'
})
export  default class CardComponent {

  // recibe el product desde el componente padre
  product = input.required<Product>(); 

  // Emite el product al componente padre
  @Output() addToCartEvent = new EventEmitter<Product>();

   addToCart(product: Product) {
    console.log(this.product)
    this.addToCartEvent.emit(product);
  }

}
