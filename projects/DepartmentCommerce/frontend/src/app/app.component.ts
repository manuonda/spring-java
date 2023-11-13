import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { ListProductosComponent } from '../features/producto/list-productos/list-productos.component';
import { ListCategoriaComponent } from '../features/categoria/list-categoria/list-categoria.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet , ListProductosComponent , ListCategoriaComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'frontend';
}
