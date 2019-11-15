package com.sgi.app.institucion.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sgi.app.institucion.modelo.Institucion;


public interface IInstitucionService {

	public List<Institucion> findByIdUsuario(Long id_usuario);

}
