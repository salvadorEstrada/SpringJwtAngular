package mx.com.devlope.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import mx.com.devlope.security.UsuarioPrincipal;

@Component	
public class JwtProvider { 
	
	private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class); 
	
	@Value ("${jwt.secret}") 
	private String secret; 
	
	@Value("${jwt.expiration}") 
	private int expiration; 
	
	
	public String generateToken(Authentication authentication ) { 
		
		UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) authentication.getPrincipal(); 
		return Jwts.builder().setSubject(usuarioPrincipal.getUsername()) 
				.setIssuedAt(new Date())
		        .setExpiration(new Date(new Date().getTime()+ expiration*1000))
		        .signWith(SignatureAlgorithm.HS256, secret)
		        .compact();
	} 
	
	public String getNombreUsuarioFromToken(String token)
	{
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
	} 
	
	
	public  boolean validateToken(String token)
	{
		try 
		{
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token); 
			return true;
			
		}catch(MalformedJwtException e) 
		{
			logger.error("Token mal formado"+ e.getMessage());
		}catch(UnsupportedJwtException ex)
		{
			logger.error("Token no soportado"+ ex.getMessage());
		} catch(ExpiredJwtException e)
		{
			logger.error("Token expirado " +e.getMessage());
		}catch (IllegalArgumentException e)
		{
			logger.error("Token vacío " +e.getMessage());
		}catch (SignatureException e)
		{
			logger.error("error en la firma " +e.getMessage());	
		}
		
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	

}
