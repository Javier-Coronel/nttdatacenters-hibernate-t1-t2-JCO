package com.nttdata.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Clase de configuración
 * 
 * @author jcoro
 *
 */
public class NTTDataHibernateUtil {
	
	private static final SessionFactory SESSION_FACTORY;
	private static final Logger LOG = LoggerFactory.getLogger(NTTDataHibernateUtil.class);
	
	/**
	 * Constructor privado
	 */
	private NTTDataHibernateUtil() {
		
	}
	
	/**
	 * Generación de la session_factory
	 */
	static {

		try {

			// Generación de configuración.
			SESSION_FACTORY = new Configuration().configure().buildSessionFactory();

		} catch (final Exception ex) {

			// Error de inicialización.
			if(LOG.isErrorEnabled())LOG.error("Configuración de Hibernate - {}", ex.toString());
			throw new ExceptionInInitializerError();
		}

	}
	
	/**
	 * Devuelve la session_factory
	 * @return SessionFactory
	 */
	public static SessionFactory getSessionFactory() {
		return SESSION_FACTORY;
	}
}
