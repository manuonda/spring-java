import { Component, inject , AfterViewInit, ViewChild, OnInit} from '@angular/core';
import { CommonModule } from '@angular/common';
import { CategoriaService } from '../services/categoria.service';
import { ICategoria } from '../models/categoria.model';
import {MatPaginator, MatPaginatorModule} from '@angular/material/paginator';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import {MatButtonModule} from '@angular/material/button';

@Component({
  selector: 'app-list-categoria',
  standalone: true,
  imports: [CommonModule,MatTableModule, MatPaginatorModule,MatButtonModule],
  templateUrl: './list-categoria.component.html',
  styleUrl: './list-categoria.component.scss'
})
export class ListCategoriaComponent implements OnInit, AfterViewInit{
   private categoriaService =inject(CategoriaService);
   private list!: ICategoria[];
   displayColumns : string[] = ["id","name","actions"];
   dataSource= new MatTableDataSource<ICategoria[]>;
   @ViewChild(MatPaginator) paginator!: MatPaginator;


   ngOnInit(): void {
       this.categoriaService.getCategorias()
       .subscribe(categorias => {
         this.dataSource= categorias;
       },error => {
        console.error("error: ",error);
       })
   }
  
   ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

}
