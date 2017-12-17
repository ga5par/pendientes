package com.pendientes.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.pendientes.model.Pendientes;

public class PendientesDAOImpl implements PendientesDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void agregarPendiente(Pendientes p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(p);

	}

	@Override
	public void actualizarPendiente(Pendientes p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(p);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pendientes> listaPendientes() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Pendientes> pendientes = session.createQuery("from Pendientes").list();
		return pendientes;
	}

	@Override
	public Pendientes obtenerPendientePorId(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Pendientes p = (Pendientes) session.load(Pendientes.class, new Integer(id));
		return p;
	}

	@Override
	public void borrarPendiente(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Pendientes p = (Pendientes) session.load(Pendientes.class, new Integer(id));
		if (null != p) {
			session.delete(p);
		}
	}

}
