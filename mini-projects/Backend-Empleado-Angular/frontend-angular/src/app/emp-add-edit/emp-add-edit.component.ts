import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { EmpleadoService } from '../services/empleado.service';
import { DialogRef } from '@angular/cdk/dialog';
import { CoreService } from '../core/core.service';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-emp-add-edit',
  templateUrl: './emp-add-edit.component.html',
  styleUrls: ['./emp-add-edit.component.scss']
})
export class EmpAddEditComponent implements OnInit {

  empForm: FormGroup;

  constructor(
    private _formBuilder: FormBuilder,
    private _empleadoService: EmpleadoService,
    private _coreService: CoreService,
    private _dialogRef: MatDialogRef<EmpAddEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any){
    this.empForm = this._formBuilder.group({
      nombre: '',
      apellido:'',
      email:'',
      fechaNacimiento:''
    })
  }

  ngOnInit(): void {
      console.log("data: " + this.data);
      this.empForm.patchValue(this.data);
  }


  onFormSubmit (){
      if( this.empForm.valid) {
        console.log(this.empForm.value);

        if ( this.data) {
            this._empleadoService.update(this.data, this.data.id).subscribe({
              next:(value) =>  {
                  this._coreService.openSnackBar("Update Empleado");
                  this._dialogRef.close(true);
              }, error : (err) =>  {
                  
              },
            });     
        } else {
          this._empleadoService.addEmployee(this.empForm.value).subscribe({
            next:(val:any) => {
              this._coreService.openSnackBar("Empleado Agregado");
              this._dialogRef.close(true);
  
            },error: (err: any) => {
              console.error("Error add empleado : ",err);
              if ( err.status === 400) {
                this._coreService.openSnackBar("Error: "+ err.error.message);
              }
            }
          })
        }
        
      };
  }

}
