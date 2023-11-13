import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment.development';
import { ICategoria } from '../models/categoria.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CategoriaService {
  
    url!: string;
   constructor( private httpClient: HttpClient) {
     this.url = environment.apiUrlServiceCategoria;
   }

    getCategorias = ():Observable<any> => {
     let url = `${this.url}/list`;
     return this.httpClient.get<ICategoria[]>(url); 
   }

   createCategoria =(categoria:ICategoria): Observable<any> => {
      let url = `${this.url}/create`;
      return this.httpClient.post(url,categoria);
   }

   updateCategoria = (categoria:ICategoria):Observable<any> => {
       let url =`${this.url}/{categoria.id}`;
       return this.httpClient.put(url, categoria);
   }
}
