package mx.com.devlope.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.devlope.entity.Usuario;
import mx.com.devlope.repositoty.UsuarioRepository;

@Service
@Transactional

public class UsuarioService  {  
	
	@Autowired 
	
	UsuarioRepository  usuarioRepository;
	
	public Optional<Usuario> getByNombreUsuario(String nu)
	{
		return usuarioRepository.findByNombreUsuario(nu);
	} 
	
	
	public boolean existePorNombre(String nu)
	{
		return usuarioRepository.existsByNombreUsuario(nu);
	} 
	
	public boolean existePorEmail(String email)
	{
	   return usuarioRepository.existsByEmail(email);
	} 
	
	public void guardar(Usuario usuario)
	{
		usuarioRepository.save(usuario);
	}
	

}
