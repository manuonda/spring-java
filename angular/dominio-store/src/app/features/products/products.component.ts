import { Component, inject } from '@angular/core';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatCardModule } from '@angular/material/card';
import { ProductService } from '@api/products.service';
import CardComponent from './card/card.component';

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [ MatGridListModule,
    MatCardModule , CardComponent],
  templateUrl: './products.component.html',
  styleUrl: './products.component.scss'
})
export default class ProductsComponent {
  private readonly productService = inject(ProductService)
  products = this.productService.products;
}
