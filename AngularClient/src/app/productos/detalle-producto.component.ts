import { Component, OnInit } from '@angular/core';
import { Producto } from '../models/producto';
import { ProductoService } from '../services/producto.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-detalle-producto',
  templateUrl: './detalle-producto.component.html',
  styleUrls: ['./detalle-producto.component.css']
})
export class DetalleProductoComponent implements OnInit { 

  producto: Producto =null;

  constructor( private productoService: ProductoService, private activateRoute: ActivatedRoute,
               private router: Router) { }

  ngOnInit() { 
    const id =this.activateRoute.snapshot.params.id; 
    this.productoService.detalle(id).subscribe(data=>{ 
      this.producto=data;
    },
    
      err=>{
        this.router.navigate(['']);
      }
    );
  } 
  //En caso que el id no exista redirija al home y volver a la lista
  volver(): void{
    window.history.back();
  }

}
