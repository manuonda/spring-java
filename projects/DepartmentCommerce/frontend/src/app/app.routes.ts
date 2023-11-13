import { Routes } from '@angular/router';
import { ProductoComponent } from '../features/producto/producto/producto.component';
import { DetailCategoriaComponent } from '../features/categoria/detail-categoria/detail-categoria.component';
import { ListCategoriaComponent } from '../features/categoria/list-categoria/list-categoria.component';

export const routes: Routes = [
    { path:'categorias', component: ListCategoriaComponent},
    { path: 'categoria', component: DetailCategoriaComponent},
    { path: 'categoria/{id}', component: DetailCategoriaComponent}
];
