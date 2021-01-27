package mx.com.devlope.repositoty;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.com.devlope.entity.Rol;
import mx.com.devlope.enums.RolNombre;

@Repository
public interface RolRepository extends JpaRepository<Rol , Long>{ 
	Optional<Rol> findByRolNombre(RolNombre rolNombre);

}
