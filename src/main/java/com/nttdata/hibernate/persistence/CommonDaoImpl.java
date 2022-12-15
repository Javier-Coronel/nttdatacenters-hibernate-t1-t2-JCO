package com.nttdata.hibernate.persistence;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Session;

/**
 * DAO genérico
 * @author jcoro
 *
 * @param <T>
 */
public abstract class CommonDaoImpl<T extends AbstractEntity> implements CommonDaoI<T>{
	private Class<T> entityClass;
	private Session session;
	
	@SuppressWarnings("unchecked")
	protected CommonDaoImpl(Session session) {
		setEntityClass((Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
		this.session = session;
	}
	
	@Override
	public void insert(final T paramT) {
		if(!session.getTransaction().isActive()) {
			session.getTransaction().begin();
		}
		
		session.save(paramT);
		session.flush();
		
		session.getTransaction().commit();
	}
	
	@Override
	public void update(final T paramT) {
		if(!session.getTransaction().isActive()) {
			session.getTransaction().begin();
		}
		
		session.saveOrUpdate(paramT);
		
		session.getTransaction().commit();
	}
	
	@Override
	public void delete(final T paramT) {
		if(!session.getTransaction().isActive()) {
			session.getTransaction().begin();
		}
		
		session.delete(paramT);
		
		session.getTransaction().commit();
	}
	
	@Override
	public T searchById(final Long id) {
		if(!session.getTransaction().isActive()) {
			session.getTransaction().begin();
		}
		
		return session.get(this.entityClass, id);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> searchAll() {
		if(!session.getTransaction().isActive()) {
			session.getTransaction().begin();
		}
		
		return session.createQuery("FROM " + this.entityClass.getName()).list();
	}
	
	/**
	 * @param entityClass The entityClass to set
	 */
	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
}
