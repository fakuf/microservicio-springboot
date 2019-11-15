package com.sgi.app.institucion.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.sgi.app.institucion.modelo.Institucion;


public interface IInstitucionDao extends Repository<Institucion, Long> {
	@Query(name = "select * from institucion where idusuario = :id_usuario")
	public List<Institucion> findByUsuarioid(Long usuarioid);

}
