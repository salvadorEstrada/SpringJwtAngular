package mx.com.devlope.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.devlope.entity.Rol;
import mx.com.devlope.enums.RolNombre;
import mx.com.devlope.repositoty.RolRepository;


@Service 
@Transactional
public class RolService { 
	
	@Autowired 
	RolRepository rolRepository;  
	
	public Optional<Rol> getByRolNombre(RolNombre rolNombre)
	{
		return rolRepository.findByRolNombre(rolNombre);
	}
	
	

}
