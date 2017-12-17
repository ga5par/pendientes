package com.pendientes.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.pendientes.dao.PendientesDAO;
import com.pendientes.model.Pendientes;

public class PendientesServiceImpl implements PendientesService {

	@Autowired
	private PendientesDAO pendientesDAO;

	public void setPendientesDAO(PendientesDAO pendientesDAO) {
		this.pendientesDAO = pendientesDAO;
	}

	@Override
	@Transactional
	public void agregarPendiente(Pendientes p) {
		this.pendientesDAO.agregarPendiente(p);
	}

	@Override
	@Transactional
	public void actualizarPendiente(Pendientes p) {
		this.pendientesDAO.actualizarPendiente(p);
	}

	@Override
	@Transactional
	public List<Pendientes> listaPendientes() {
		return this.pendientesDAO.listaPendientes();
	}

	@Override
	@Transactional
	public Pendientes obtenerPendientePorId(int id) {
		return this.pendientesDAO.obtenerPendientePorId(id);
	}

	@Override
	@Transactional
	public void borrarPendiente(int id) {
		this.pendientesDAO.borrarPendiente(id);
	}

}
