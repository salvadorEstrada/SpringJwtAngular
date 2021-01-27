package mx.com.devlope.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity 
@Table (name="producto")
public class Producto { 
	
	@Id  
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id; 
	
	@NotBlank 
	@Column(unique=true) 
	private String nombreProducto; 
	
	@NotNull  
	private int precio;
	
	 public Producto() 
	 {
	 }
	
	 public Producto(String nombreProducto, int precio) {
	        this.nombreProducto = nombreProducto;
	        this.precio = precio;
	    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	} 
	
	

}
