import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmpleadoService {

  constructor(private _http: HttpClient) { 

  }

  addEmployee = (data: any):Observable<any> => {
    return this._http.post('http://localhost:8080/api/v1/empleado/create',data);
  }
  update = (data: any, id:number): Observable<any> => {
    return this._http.put('http://localhost:8080/api/v1/empleado/'+id,data);
  }

  delete =(id:number):Observable<any> => {
     return this._http.delete(`http://localhost:8080/api/v1/empleado/${id}`);
  }  
  getAll = () :Observable<any> => {
      return this._http.get('http://localhost:8080/api/v1/empleado/list');
  }
} 
