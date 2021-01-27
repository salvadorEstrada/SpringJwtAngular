package mx.com.devlope.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.com.devlope.DTO.Mensaje;
import mx.com.devlope.entity.Producto;
import mx.com.devlope.service.ProductoService;




@RestController
@RequestMapping("/api/productos") 
@CrossOrigin(origins = "http://localhost:4200")
public class ProductoController { 
	
	@Autowired
	ProductoService productoService; 
	
	@GetMapping("/lista") 
	public ResponseEntity<List<Producto>> getLista()
	{
		List<Producto> lista = productoService.obtenerTodos(); 
		return  new ResponseEntity<List<Producto>>(lista, HttpStatus.OK);
	}  
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("detalle/{id}") 
	
		public ResponseEntity<Producto> getOne(@PathVariable Long id)
		{
		if(!productoService.existPorId(id))
			return new ResponseEntity(new Mensaje("No existe ese producto"),HttpStatus.NOT_FOUND);
		Producto producto  = productoService.obtenerPorId(id).get(); 
		return new ResponseEntity<Producto>(producto, HttpStatus.OK);
	}
	
	//Validaciones para crear un nuevo producto
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("nuevo")  
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?>create(@RequestBody Producto producto) 
	{
	    if(StringUtils.isBlank(producto.getNombreProducto())) 
	    	return new ResponseEntity(new Mensaje("El nombre es obligatorio"),HttpStatus.BAD_REQUEST);
	    
	    if((Integer)producto.getPrecio()==null ||producto.getPrecio()==0)
	    	return new ResponseEntity(new Mensaje("El precio es obligatorio!"), HttpStatus.BAD_REQUEST); 
	    
	    if(productoService.existPorNombre(producto.getNombreProducto())) 
	    	return new ResponseEntity(new Mensaje("The product name already exits!"),HttpStatus.BAD_REQUEST);
	    
	    productoService.guardar(producto); 
	    return new ResponseEntity(new Mensaje("Producto guardado!"),HttpStatus.CREATED);
	} 
	
	//Actualizar un producto
	
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@PutMapping("/actualizar/{id}") 
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(@RequestBody Producto producto, @PathVariable("id") Long id){
        if(!productoService.existPorId(id))
            return new ResponseEntity(new Mensaje("no existe ese producto"), HttpStatus.NOT_FOUND);
        if(StringUtils.isBlank(producto.getNombreProducto()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if((Integer)producto.getPrecio() == null || producto.getPrecio()==0)
            return new ResponseEntity(new Mensaje("el precio es obligatorio"), HttpStatus.BAD_REQUEST);
        if(productoService.existPorNombre(producto.getNombreProducto()) &&
                productoService.obtenerPorNombre(producto.getNombreProducto()).get().getId() != id)
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        Producto prodUpdate = productoService.obtenerPorId(id).get();
        prodUpdate.setNombreProducto(producto.getNombreProducto());
        prodUpdate.setPrecio(producto.getPrecio());
        productoService.guardar(prodUpdate);
        return new ResponseEntity(new Mensaje("producto actualizado"), HttpStatus.CREATED);
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@DeleteMapping("/borrar/{id}") 
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> delete(@PathVariable Long id)
	{
		if(!productoService.existPorId(id))
		{
			return new ResponseEntity(new Mensaje("No existe ese producto"),HttpStatus.NOT_FOUND);
		}
		productoService.borrar(id); 
		return new ResponseEntity (new Mensaje("producto eliminado"), HttpStatus.OK);
	}

}
