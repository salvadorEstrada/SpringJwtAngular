import { Component, OnInit } from '@angular/core';
import { Producto } from '../models/producto';
import { ProductoService } from '../services/producto.service';

@Component({
  selector: 'app-nuevo-producto',
  templateUrl: './nuevo-producto.component.html',
  styleUrls: ['./nuevo-producto.component.css']
})
export class NuevoProductoComponent implements OnInit { 

  form: any = {}; 
  producto: Producto; 
  creado = false; 

  failProducto = false; 
  mensajeFail =  ''; 
  mensajeOk = '';

  constructor(private productoService: ProductoService) { }

  ngOnInit() {
  } 

  onCreate(): void{

     this.productoService.crear(this.form).subscribe(data=>{
      this.mensajeOk=data.mensaje;
      this.creado=true; 
      this.failProducto= false;
    }, 

    (err: any)=>{
      this.mensajeFail= err.error.mensaje;
      this.creado= false; 
      this.failProducto=true;
      
    }
    
    );
  } 

  volver(): void{
    window.history.back();
  }


}
