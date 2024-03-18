import { HttpClient } from "@angular/common/http";
import { EnvironmentInjector, Injectable, inject, runInInjectionContext, signal } from "@angular/core";
import { environment } from "@envs/environment.development";
import { Product } from "@shared/models/product.interface";
import { tap } from "rxjs";
import { toSignal } from "@angular/core/rxjs-interop";



@Injectable({providedIn: 'root'})
export class ProductService {
  public products = signal<any>([])

  private readonly _http = inject(HttpClient);
  private readonly _endPoint = environment.apiURL;
  private readonly _injector = inject(EnvironmentInjector);

  constructor(){
    this.getProducts()
  }

  public getProducts(): void{
     this._http.get<Product[]>(`${this._endPoint}/products/?sort=desc`)
     .pipe(
      tap((data:any[]) => this.products.set(data)))
     .subscribe()
  }

  public getProductById(id: number){
    const product$ = this._http.get<Product>(`${this._endPoint}/products/${id}`);
    return runInInjectionContext(this._injector,() =>  toSignal<Product>(product$));

  }
  
}