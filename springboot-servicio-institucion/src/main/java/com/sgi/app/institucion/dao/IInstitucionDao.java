package com.sgi.app.institucion.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.sgi.app.institucion.modelo.Institucion;


public interface IInstitucionDao extends CrudRepository<Institucion, Long> {
	@Query(name = "select i from institucion i where i.usuarioid = ?1")
	public List<Institucion> findByUsuarioid(Long usuarioid);

}
