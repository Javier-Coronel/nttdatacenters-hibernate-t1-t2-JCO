package com.nttdata.hibernate.services;

import java.util.List;

import com.nttdata.hibernate.persistence.Cliente;

/**
 * 
 * Interface del servicio de clientes.
 *  
 * @author jcoro
 *
 */
public interface ClienteManagementServiceI {
	
	/**
	 * Inserta un nuevo cliente
	 * @param insertedCliente El cliente a añadir en la base de datos
	 */
	public void insertCliente(final Cliente insertedCliente);
	
	/**
	 * Actualiza un cliente
	 * @param updatedCliente El cliente a actualizar.
	 */
	public void updateCliente(final Cliente updatedCliente);
	
	/**
	 * Elimina un cliente
	 * @param deletedCliente
	 */
	public void deleteCliente(final Cliente deletedCliente);
	
	/**
	 * Busca un cliente por su id
	 * @param id
	 * @return Cliente El cliente con el id dado.
	 */
	public Cliente searchById(final Long id);
	
	/**
	 * Busca todos los clientes de la base de datos.
	 * @return List<Cliente> Todos los clientes de la base de datos.
	 */
	public List<Cliente> buscarTodos();
	
	/**
	 * Busca todos los clientes que tienen el nombre dado y el apellido dado
	 * @param nombre
	 * @param apellidoUno
	 * @return List<Cliente>
	 */
	public List<Cliente> buscarPorNombreYApellidoUno(final String nombre, String apellidoUno);
	
	
	/**
	 * Busca todos los clientes que tienen el nombre dado y los apellidos dados
	 * @param nombre
	 * @param apellidoUno
	 * @param apellidoDos
	 * @return List<Cliente>
	 */
	public List<Cliente> buscarPorNombreYApellidos(final String nombre, String apellidoUno, String apellidoDos);
	
	/**
	 * Devuelve el cliente que tiene el documento de identidad dado
	 * @param documentoIdentidad
	 * @return Cliente
	 */
	public Cliente buscarPorDocumentoIdentificacion(final String documentoIdentidad);
}
