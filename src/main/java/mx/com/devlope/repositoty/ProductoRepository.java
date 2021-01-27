package mx.com.devlope.repositoty;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.com.devlope.entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> { 
	
	Optional<Producto> findByNombreProducto(String np); 
	
	boolean existsByNombreProducto(String np); 
	
	

}
