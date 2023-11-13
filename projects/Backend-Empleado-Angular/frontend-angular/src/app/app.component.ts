import { MatDialog } from '@angular/material/dialog';
import { EmpAddEditComponent } from './emp-add-edit/emp-add-edit.component';
import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator, MatPaginatorModule} from '@angular/material/paginator';
import {MatSort, MatSortModule} from '@angular/material/sort';
import {MatTable, MatTableDataSource, MatTableModule} from '@angular/material/table';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { EmpleadoService } from './services/empleado.service';



export interface Empleado {
  id: number;
  nombre: string;
  apellido: string;
  email: string; 
}



@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],

})
export class AppComponent implements OnInit {
  title = 'frontend-angular';
  displayedColumns: string[] = ['id','nombre', 'apellido', 'email','actions'];
  dataSource: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private _dialog: MatDialog,
    private _empleadoService: EmpleadoService){
     

      // Assign the data to the data source for the table to render
      this.dataSource = new MatTableDataSource<Empleado>();
  }
  ngOnInit(): void {
   this.getEmpleadoList();
    
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  getEmpleadoList() {
    this._empleadoService.getAll().subscribe({
      next: (value:any)  => {
          console.log("Empleado list : ",value);
          this.dataSource  = new MatTableDataSource<Empleado>(value);
          this.dataSource.sort = this.sort;
          this.dataSource.paginator = this.paginator;
      },error(err) {
          console.error("Error : ",err);
      },
    });
  }

  openAddEditEmpForm(){
    const dialogRef = this._dialog.open(EmpAddEditComponent);
    dialogRef.afterClosed().subscribe({
       next : (value) =>  {
           if(value) {
            this.getEmpleadoList();
           }
       },error: (err) =>  {
           console.error("openAddEditEmpForm");
       },
    });
  }

  deleteEmployee(id: number){
    this._empleadoService.delete(id).subscribe({
      next: (value)  => {
          alert("Empleado deleted");
          this.getEmpleadoList();
      },error: (err)  => {
          console.error("Error deleting employeee",err);
      },
    })
  }

  openEditForm(data:Empleado) {
     this._dialog.open(EmpAddEditComponent,{
       data
     });
  }
}
