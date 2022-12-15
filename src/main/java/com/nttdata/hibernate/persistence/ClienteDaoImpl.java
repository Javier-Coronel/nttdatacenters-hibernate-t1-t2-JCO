package com.nttdata.hibernate.persistence;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
/**
 * Implementacion del DAO de la tabla Cliente
 * @author jcoro
 *
 */
public class ClienteDaoImpl extends CommonDaoImpl<Cliente> implements ClienteDaoI {
	
	/** Sesión de conexión a BD */
	private Session session;
	
	/**
	 * Metodo constructor
	 * @param session
	 */
	public ClienteDaoImpl(Session session) {
		super(session);
		this.session = session;
	}
	
	@Override
	public List<Cliente> buscarPorNombreYApellidoUno(String nombre, String apellidoUno) {
		
		if(!session.getTransaction().isActive()) {
			session.getTransaction().begin();
		}
		final CriteriaBuilder cb = session.getCriteriaBuilder();
		final CriteriaQuery<Cliente> cquery = cb.createQuery(Cliente.class);
		final Root<Cliente> rootC = cquery.from(Cliente.class);
		final Predicate pr1 = cb.like(rootC.get("nombre"), nombre);
		final Predicate pr2 = cb.like(rootC.get("apellidoUno"), apellidoUno);
		cquery.select(rootC).where(cb.and(pr1,pr2));
		
		return session.createQuery(cquery).getResultList();
		
	}
	
	@Override
	public List<Cliente> buscarPorNombreYApellidos(String nombre, String apellidoUno, String apellidoDos) {

		if(!session.getTransaction().isActive()) {
			session.getTransaction().begin();
		}
		
		
		final CriteriaBuilder cb = session.getCriteriaBuilder();
		final CriteriaQuery<Cliente> cquery = cb.createQuery(Cliente.class);
		final Root<Cliente> rootC = cquery.from(Cliente.class);
		final Predicate pr1 = cb.like(rootC.get("nombre"), nombre);
		final Predicate pr2 = cb.like(rootC.get("apellidoUno"), apellidoUno);
		final Predicate pr3 = cb.like(rootC.get("apellidoDos"), apellidoDos);
		cquery.select(rootC).where(cb.and(pr1,pr2,pr3));
		
		return session.createQuery(cquery).getResultList();
		
	}
	
	@Override
	public Cliente buscarPorDocumentoIdentificacion(String documentoIdentidad) {
		
		if(!session.getTransaction().isActive()) {
			session.getTransaction().begin();
		}
		
		return (Cliente)session.createQuery("FROM Cliente WHERE IDENTIDAD_CLIENTE = " + documentoIdentidad).list().get(0);
		  
	}

}
