package mx.com.devlope.repositoty;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.com.devlope.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> { 
	Optional<Usuario> findByNombreUsuario(String nu); 
	boolean existsByNombreUsuario(String nu); 
	boolean existsByEmail(String email);

}
