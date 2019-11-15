package com.smh.app.usuarios.modelos.daos;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.smh.app.usuario.commons.modelo.entidades.Usuario;

//PagingAndSortingRepository extiende de crudrepository. Brinda mas funcionalidad como ordenar y paginar

@RepositoryRestResource(path="usuarios")
public interface UsuarioDao extends PagingAndSortingRepository<Usuario, Long> {
	
	@RestResource(path="buscar-username")
	public Usuario findByUsername(@Param("username") String username);

}
