package com.sgi.app.institucion.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sgi.app.institucion.modelo.Institucion;
import com.sgi.app.institucion.servicio.IInstitucionService;

@RestController
public class InstitucionController {
	@Autowired
	private IInstitucionService institucionService;
	
	@GetMapping("/usuarios/{idUsuario}/instituciones")
	public List<Institucion> listar(@PathVariable Long idUsuario){
		return institucionService.findByIdUsuario(idUsuario).stream().map(i ->{
			//p.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
			return i;
		}).collect(Collectors.toList());
	}
}
