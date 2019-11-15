package com.smh.app.oauth.servicios;

import com.smh.app.usuario.commons.modelo.entidades.Usuario;

public interface IUsuarioService {
	
	/**
	 * 
	 * @param username
	 * @return
	 */
	public Usuario findByUsername(String username);
	
	public Usuario update(Usuario usuario, Long id);
}
