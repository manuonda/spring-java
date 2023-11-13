import { FormControl } from "@angular/forms";

export interface ICategoria {
    id:number;
    name:string;
    description?:string;
}


export interface ICategoriaDataForm {
	name: FormControl<string>;
	description: FormControl<string | null>;
}