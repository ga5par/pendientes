package com.pendientes.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pendientes.model.Pendientes;
import com.pendientes.service.PendientesService;

@RestController
public class PendientesController {

	private PendientesService pendientesService;

	@Autowired(required = true)
	@Qualifier(value = "pendientesService")
	public void setPendientesService(PendientesService ps) {
		this.pendientesService = ps;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}

	@RequestMapping(value = "/lista", method = RequestMethod.GET, produces = "application/json")
	public List<Pendientes> obtenerPendientes() {
		List<Pendientes> pendientes = pendientesService.listaPendientes();
		Collections.sort(pendientes, new Comparator<Pendientes>() {
			public int compare(Pendientes p1, Pendientes p2) {
				return p1.getId() > p2.getId() ? -1 : 1;
			}
		});
		return pendientes;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/nuevo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity nuevo(@RequestBody Pendientes instancia) {
		if (instancia.getId() == 0) {
			pendientesService.agregarPendiente(instancia);
		} else {
			pendientesService.actualizarPendiente(instancia);
		}

		return new ResponseEntity(HttpStatus.OK);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/actualizar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity actualizar(@RequestBody Pendientes instancia) {
		pendientesService.actualizarPendiente(instancia);
		return new ResponseEntity(HttpStatus.OK);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/borrar", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity borrar(@RequestBody Pendientes instancia) {
		pendientesService.borrarPendiente(instancia.getId());
		return new ResponseEntity(HttpStatus.OK);
	}

}
