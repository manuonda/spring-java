import { HttpClient } from "@angular/common/http";
import { Injectable, inject, signal } from "@angular/core";
import { environment } from "@envs/environment.development";
import { Product } from "@shared/models/product.interface";
import { tap } from "rxjs";


@Injectable({providedIn: 'root'})
export class ProductService {
  public products = signal<any>([])

  private readonly _http = inject(HttpClient);
  private readonly _endPoint = environment.apiURL;

  constructor(){
    this.getProducts()
  }

  public getProducts(): void{
     this._http.get<Product[]>(`${this._endPoint}/products/?sort=desc`)
     .pipe(
      tap((data:any[]) => this.products.set(data)))
     .subscribe()
  }

  public getProductById(id: string){
    return this._http.get<Product>(`${this._endPoint}/products/${id}`);
  }
  
}