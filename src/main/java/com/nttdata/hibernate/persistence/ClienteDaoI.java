package com.nttdata.hibernate.persistence;

import java.util.List;
/**
 * Interface del DAO de la tabla Cliente
 * @author jcoro
 *
 */
public interface ClienteDaoI extends CommonDaoI<Cliente>{
	
	/**
	 * Devuelve todos los clientes que tengan un nombre especifico y el primer apellido igual.
	 * @param nombre
	 * @param apellidoUno
	 * @return List<Cliente>
	 */
	public List<Cliente> buscarPorNombreYApellidoUno(final String nombre, final String apellidoUno);
	
	/**
	 * Devuelve todos los clientes que tengan un nombre y apellidos especificos.
	 * @param nombre
	 * @param apellidoUno
	 * @param apellidoDos
	 * @return List<Cliente>
	 */
	public List<Cliente> buscarPorNombreYApellidos(final String nombre, final String apellidoUno, final String apellidoDos);
	
	/**
	 * Devuelve un cliente que tenga un documento de identificacion especifico.
	 * @param documentoIdentidad
	 * @return Cliente
	 */
	public Cliente buscarPorDocumentoIdentificacion(final String documentoIdentidad);
	
}
