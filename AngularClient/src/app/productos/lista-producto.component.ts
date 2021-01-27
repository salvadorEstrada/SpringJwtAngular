import { Component, Input, OnInit } from '@angular/core';
import { ProductoService } from '../services/producto.service'; 
import { Producto } from '../models/producto';


@Component({
  selector: 'app-lista-producto',
  templateUrl: './lista-producto.component.html',
  styleUrls: ['./lista-producto.component.css']
})
export class ListaProductoComponent implements OnInit { 

  @Input() user: string;

  productos: Producto[] = []; 


  constructor(private productoService: ProductoService) { }

  ngOnInit() { 
    this.cargarProductos();
  } 

  cargarProductos(): void {
    this.productoService.lista().subscribe(data=>{
       this.productos = data;
    } ,
      (err:  any)=>{
        console.log(err);
      }
    );
  } 
  onDelete(id: number): void{
    if(confirm('¿Estas seguro de eliminar?')){
      this.productoService.borrar(id).subscribe(data=>{
        this.cargarProductos(); //Carga de nuevo la lista, ya sin el producto eliminado
      });
    }
  }

}
