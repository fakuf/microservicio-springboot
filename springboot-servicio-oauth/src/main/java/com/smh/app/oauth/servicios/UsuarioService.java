package com.smh.app.oauth.servicios;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.smh.app.oauth.clientes.UsuarioFeignCliente;
import com.smh.app.usuario.commons.modelo.entidades.Usuario;

import feign.FeignException;

@Service
public class UsuarioService implements IUsuarioService, UserDetailsService {
	private Logger log =  LoggerFactory.getLogger(UsuarioService.class);
	@Autowired
	private UsuarioFeignCliente client;

	@Override
	public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
		try {
			Usuario usr = client.findByUsername(usuario);

			List<GrantedAuthority> authorities = usr.getRoles().stream()
					.map(rol -> new SimpleGrantedAuthority(rol.getNombre()))
					.peek(authority -> log.info("Rol: " + authority.getAuthority())).collect(Collectors.toList());
			log.info("Usuario autenticado " + usr);
			return new User(usr.getUsername(), usr.getPassword(), usr.getHabilitado(), true, true, true, authorities);
		} 
		
		catch (FeignException e) {
			log.error("No existe el usuario '"+usuario+"' en el sistema");
			throw new UsernameNotFoundException("No existe el usuario '"+usuario+"' en el sistema");
		}
	}

	@Override
	public Usuario findByUsername(String username) {
		return client.findByUsername(username);
	}

	@Override
	public Usuario update(Usuario usuario, Long id) {
		return client.update(usuario, id);
	}

}
