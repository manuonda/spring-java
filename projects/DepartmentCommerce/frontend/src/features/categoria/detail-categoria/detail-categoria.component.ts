import { Component, inject } from '@angular/core';
import {
	AbstractControl,
	ControlValueAccessor,
	NG_VALIDATORS,
	NG_VALUE_ACCESSOR,
	NonNullableFormBuilder,
	ReactiveFormsModule,
	ValidationErrors,
	Validator,
	Validators
} from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import {MatIconModule} from '@angular/material/icon';
import {MatDividerModule} from '@angular/material/divider';
import {MatButtonModule} from '@angular/material/button';

import { CommonModule } from '@angular/common';
import {MatCardModule} from '@angular/material/card'; 
import { ICategoria, ICategoriaDataForm } from '../models/categoria.model';
import { CategoriaService } from '../services/categoria.service';

@Component({
  selector: 'app-detail-categoria',
  standalone: true,
  imports: [CommonModule, 
            MatCardModule, 
            ReactiveFormsModule, 
            MatFormFieldModule, 
            MatInputModule,
            MatButtonModule, MatDividerModule, MatIconModule],
  templateUrl: './detail-categoria.component.html',
  styleUrl: './detail-categoria.component.scss'
})
// Class Validator : se propaga hacia el padre los errores
export class DetailCategoriaComponent {
   
  private categoriaService = inject(CategoriaService);

  // private _formBuilder = inject(FormBuilder);   
  private _formBuilder = inject(NonNullableFormBuilder);

  formGroup = this._formBuilder.group<ICategoriaDataForm>({
     name:  this._formBuilder.control('', { validators: [Validators.required] }),
     description: this._formBuilder.control('') 
  });


 
  saveData(){
    console.log("this.saveData : ", this.formGroup.value);
    console.log(this.formGroup.valid);
    if (this.formGroup.valid) {

       const categoriaData: ICategoria = {
        name : this.formGroup.get('name')?.value?? '',
        description: this.formGroup.get('description')?.value ?? ''
       };
       this.categoriaService.createCategoria(categoriaData)
       .subscribe((data) =>  {
          console.log("data : ",data);
       }, error => {
          console.error("error :",error);  
       });
    

    } 
  }
}
