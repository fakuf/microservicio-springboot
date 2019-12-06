package com.sgi.app.institucion.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgi.app.institucion.dao.IInstitucionDao;
import com.sgi.app.institucion.modelo.Institucion;

@Service
public class InstitucionServiceImpl implements IInstitucionService{

	@Autowired
	private IInstitucionDao institucionDao;
	
	@Override
	public List<Institucion> findByIdUsuario(Long idusuario) {
		return institucionDao.findByUsuarioid(idusuario);
		
	}
	
	@Override
	public Institucion guardar(Institucion institucion) {
		return institucionDao.save(institucion);
	}

	@Override
	public Institucion modificar(Institucion institucion) {
		return institucionDao.save(institucion);
	}


}
