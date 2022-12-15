package com.nttdata.hibernate.persistence;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Join;

import org.hibernate.Session;
/**
 * Implementacion del DAO de la tabla Contrato
 * @author jcoro
 *
 */
public class ContratoDaoImpl extends CommonDaoImpl<Contrato> implements ContratoDaoI {
	
	/** Sesión de conexión a BD */
	private Session session;
	
	/**
	 * Metodo constructor
	 * @param session
	 */
	public ContratoDaoImpl(Session session) {
		super(session);
		this.session = session;
	}

	@Override
	public List<Contrato> buscarPorFechaDeVigencia(Date fechaDeVigencia) {
		if(!session.getTransaction().isActive()) {
			session.getTransaction().begin();
		}
		
		final CriteriaBuilder cb = session.getCriteriaBuilder();
		final CriteriaQuery<Contrato> cquery = cb.createQuery(Contrato.class);
		final Root<Contrato> rootC = cquery.from(Contrato.class);
		final Predicate pr = cb.equal(rootC.get("fechaDeVigencia"), fechaDeVigencia);
		cquery.select(rootC).where(cb.and(pr));
		return session.createQuery(cquery).getResultList();
	}

	@Override
	public List<Contrato> buscarPorFechaDeCaducidad(Date fechaDeCaducidad) {
		if(!session.getTransaction().isActive()) {
			session.getTransaction().begin();
		}
		
		final CriteriaBuilder cb = session.getCriteriaBuilder();
		final CriteriaQuery<Contrato> cquery = cb.createQuery(Contrato.class);
		final Root<Contrato> rootC = cquery.from(Contrato.class);
		final Predicate pr = cb.equal(rootC.get("fechaDeCaducidad"), fechaDeCaducidad);
		cquery.select(rootC).where(cb.and(pr));
		return session.createQuery(cquery).getResultList();
	}

	@Override
	public List<Contrato> buscarPorPrecioMensual(float precioMensual) {
		if(!session.getTransaction().isActive()) {
			session.getTransaction().begin();
		}
		
		final CriteriaBuilder cb = session.getCriteriaBuilder();
		final CriteriaQuery<Contrato> cquery = cb.createQuery(Contrato.class);
		final Root<Contrato> rootC = cquery.from(Contrato.class);
		final Predicate pr = cb.equal(rootC.get("precioMensual"), precioMensual);
		cquery.select(rootC).where(cb.and(pr));
		return session.createQuery(cquery).getResultList();
	}

	@Override
	public List<Contrato> buscarPorNombreDeClienteYPrecio(String nombre, float precio) {
		if(!session.getTransaction().isActive()) {
			session.getTransaction().begin();
		}
		
		final CriteriaBuilder cb = session.getCriteriaBuilder();
		final CriteriaQuery<Contrato> cquery = cb.createQuery(Contrato.class);
		final Root<Contrato> rootC = cquery.from(Contrato.class);
		final Join<Contrato, Cliente> contratoJoinCliente = rootC.join("cliente");
		
		final Predicate pr1 = cb.like(contratoJoinCliente.get("nombre"), nombre);
		final Predicate pr2 = cb.equal(rootC.get("precioMensual"), precio);
		
		cquery.select(rootC).where(cb.and(pr1,pr2));
		
		return session.createQuery(cquery).getResultList();
	}

	@Override
	public List<Contrato> buscarPorCliente(Cliente cliente) {
		if(!session.getTransaction().isActive()) {
			session.getTransaction().begin();
		}

		final CriteriaBuilder cb = session.getCriteriaBuilder();
		final CriteriaQuery<Contrato> cquery = cb.createQuery(Contrato.class);
		final Root<Contrato> rootC = cquery.from(Contrato.class);
		final Join<Contrato, Cliente> contratoJoinCliente = rootC.join("cliente");
		final Predicate pr = cb.equal(contratoJoinCliente.get("clienteId"), cliente.getClienteId());
		cquery.select(rootC).where(cb.and(pr));
		return session.createQuery(cquery).getResultList();
	}

}
