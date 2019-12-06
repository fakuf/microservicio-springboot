package com.sgi.app.institucion.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sgi.app.institucion.modelo.Institucion;


public interface IInstitucionService {
	/**
	 * 
	 * @param id_usuario para recuperar sus instituciones
	 * @return una lista de instituciones
	 */
	public List<Institucion> findByIdUsuario(Long id_usuario);
	
	/**
	 * Institucion a guardar
	 * @param institucion
	 * @return
	 */
	public Institucion guardar(Institucion institucion);
	
	/**
	 * 
	 * @param institucion
	 * @return
	 */
	public Institucion modificar(Institucion institucion);

}
