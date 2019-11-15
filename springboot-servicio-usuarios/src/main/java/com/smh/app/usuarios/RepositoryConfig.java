package com.smh.app.usuarios;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import com.smh.app.usuario.commons.modelo.entidades.Rol;
import com.smh.app.usuario.commons.modelo.entidades.Usuario;

@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer{

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {

		config.exposeIdsFor(Usuario.class, Rol.class);
	}

}
