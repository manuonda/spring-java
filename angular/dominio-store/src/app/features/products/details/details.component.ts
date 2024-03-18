import { CurrencyPipe } from '@angular/common';
import { Component, Input, OnInit, Signal, inject, input  } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { DomSanitizer, SafeHtml } from '@angular/platform-browser';
import { RouterLink } from '@angular/router';
import { ProductService } from '@api/products.service';
import { Product } from '@shared/models/product.interface';

@Component({
  selector: 'app-details',
  standalone: true,
  imports: [MatIconModule,CurrencyPipe,MatCardModule,MatButtonModule, RouterLink],
  templateUrl: './details.component.html',
  styleUrl: './details.component.scss'
})
export  default class DetailsComponent  implements OnInit{
 

   // esto es como se obtiene como parametro
   //@Input({alias:'id'}) productId!: number;
   // esto es como se obtiene con el nuevo signal 
   productId = input<number>(0, {alias: 'id'}); 
   product! : Signal<Product | undefined> ;
   private readonly productsSvc = inject(ProductService)
   private readonly sanitizer = inject(DomSanitizer);

   starsArray = new Array(5).fill(0);

   ngOnInit(): void {
     // call service
     this.product = this.productsSvc.getProductById(this.productId());
  }
      

  addToCart =() => {

  }

  generateSVG(index: number): SafeHtml {
    let svgContent = '';
    const rate = this.product()?.rating.rate as number; 
  
    if (index + 1 <= Math.floor(rate)) {
      console.log("aqui 1");
      svgContent = `<mat-icon class="star-icon">star</mat-icon>`;
    } else if (index < rate) {
      console.log("aqui 2");
      svgContent = `<mat-icon class="star-icon">star_half</mat-icon>`;
    } else {
      console.log("aqui 3")
      svgContent = `<mat-icon class="star-icon">star_border</mat-icon>`;
    }
  
    console.log(svgContent)
    //return this.sanitizer.bypassSecurityTrustHtml(svgContent);
    return svgContent; // Return string directly, no need for bypassSecurityTrustHtml 
  }

  generateStarArray(): SafeHtml[] {
    const starArray: SafeHtml[] = [];
    for (let i = 0; i < 5; i++) {
      starArray.push(this.generateSVG(i));
    }
    return starArray;
  }
  
  
}
