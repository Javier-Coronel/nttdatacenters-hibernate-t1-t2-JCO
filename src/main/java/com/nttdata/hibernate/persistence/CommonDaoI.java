package com.nttdata.hibernate.persistence;

import java.util.List;

/**
 * DAO genérico
 * @author jcoro
 * @param <T>
 */
public interface CommonDaoI<T> {
	
	/**
	 * Inserta un registro en la BBDD.
	 * @param paramT
	 */
	public void insert(final T paramT);
	
	/**
	 * Actualiza un registro en la BBDD.
	 * @param paramT
	 */
	public void update(final T paramT);
	
	/**
	 * Elimina un registro en la BBDD.
	 * @param paramT
	 */
	public void delete(final T paramT);
	
	/**
	 * Busca un registro de la BBDD por id.
	 * @param id
	 * @return T
	 */
	public T searchById(final Long id);
	
	/**
	 * Busqueda de todos los registros en la BBDD.
	 * @return List<T>
	 */
	public List<T> searchAll();
}
