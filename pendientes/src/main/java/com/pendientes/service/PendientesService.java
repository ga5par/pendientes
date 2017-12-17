package com.pendientes.service;

import java.util.List;

import com.pendientes.model.Pendientes;

public interface PendientesService {

	public void agregarPendiente(Pendientes p);

	public void actualizarPendiente(Pendientes p);

	public List<Pendientes> listaPendientes();

	public Pendientes obtenerPendientePorId(int id);

	public void borrarPendiente(int id);

}
