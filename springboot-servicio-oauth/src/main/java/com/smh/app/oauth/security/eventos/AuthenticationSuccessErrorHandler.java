package com.smh.app.oauth.security.eventos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.smh.app.oauth.servicios.IUsuarioService;
import com.smh.app.usuario.commons.modelo.entidades.Usuario;

import feign.FeignException;

@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {

	private Logger log = LoggerFactory.getLogger(AuthenticationSuccessErrorHandler.class);

	@Autowired
	private IUsuarioService usuarioService;

	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
		UserDetails user = (UserDetails) authentication.getPrincipal();
		log.info("Se autentico con exito el usuario "+ user.getUsername());
		Usuario usuario = usuarioService.findByUsername(authentication.getName());
		if(usuario.getIntentos() != null && usuario.getIntentos() > 0 ) {
			usuario.setIntentos(0);
			usuarioService.update(usuario, usuario.getId());
		}
	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		log.error(String.format("Error en el login", exception.getMessage()));

		try {
			Usuario usuario = usuarioService.findByUsername(authentication.getName());
			if (usuario.getIntentos() == null) {
				usuario.setIntentos(0);
			}
			usuario.setIntentos(usuario.getIntentos() + 1);
			if (usuario.getIntentos() >=3 ) {
				usuario.setHabilitado(false);
				log.error(String.format("Realizo los tres intentos", usuario.getUsername()));
				System.out.println("Realizo los tres intentos");
			}
			usuarioService.update(usuario, usuario.getId());
		} catch (FeignException e) {
			log.error(String.format("El usuario %s no existe en el sistema", authentication.getName()));
		}

	}

}
