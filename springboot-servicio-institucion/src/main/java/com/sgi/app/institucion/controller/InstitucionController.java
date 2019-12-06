package com.sgi.app.institucion.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sgi.app.institucion.modelo.Institucion;
import com.sgi.app.institucion.servicio.IInstitucionService;

//Nos permite actualizar todos los componentes, controladores, etc que se le etsan inyectando configuraciones 
@RefreshScope
@RestController
public class InstitucionController {
	@Autowired
	private IInstitucionService institucionService;
	
	@Autowired
	private Environment env;
	
	@Value("${configuracion.texto}")
	private String texto;
	
	@Value("${server.port}")
	private String puerto;
	
	@PostMapping(path = "/", consumes = "application/json", produces = "application/json")
	public Institucion guardar(@RequestBody Institucion institucion) {
		return institucionService.guardar(institucion);
	}
	
	@PutMapping(path="/", consumes="application/json", produces="application/json")
	public Institucion modificar (@RequestBody Institucion institucion) {
		if (institucion.getId() == null)
			return null;
		return institucionService.modificar(institucion);
	}
	
	@GetMapping("/usuarios/{idUsuario}/instituciones")
	public List<Institucion> listar(@PathVariable Long idUsuario){
		return institucionService.findByIdUsuario(idUsuario).stream().map(i ->{
			//p.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
			return i;
		}).collect(Collectors.toList());
	}
	
	@GetMapping("/configuracion")
	public ResponseEntity<?> obtenerConfiguracion(){
		Map<String, String> json = new HashMap<String, String>();
		json.put("texto", texto);
		json.put("puerto", puerto);
		
		if(env.getActiveProfiles().length > 0 && env.getActiveProfiles()[0].equals("dev")) {
			json.put("autor.nombre", env.getProperty("configuracion.autor.nombre"));
			json.put("autor.email", env.getProperty("configuracion.autor.email"));
		}
		return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
	}

}
