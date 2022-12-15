package com.nttdata.hibernate.services;

import java.util.Date;
import java.util.List;

import com.nttdata.hibernate.persistence.Cliente;
import com.nttdata.hibernate.persistence.Contrato;

/**
 * 
 * Interface del servicio de contrato.
 * 
 * @author jcoro
 *
 */
public interface ContratoManagementServiceI {
	
	/**
	 * Inserta un nuevo contrato
	 * @param insertedContrato
	 */
	public void insertContrato(final Contrato insertedContrato);
	
	/**
	 * Actualiza un contrato
	 * @param updatedContrato
	 */
	public void updateContrato(final Contrato updatedContrato);
	
	/**
	 * Elimina un contrato
	 * @param deletedContrato
	 */
	public void deleteContrato(final Contrato deletedContrato);
	
	/**
	 * Busca un contrato por su id
	 * @param id
	 * @return Contrato El contrato con el id dado
	 */
	public Contrato searchById(final Long id);
	
	/**
	 * Busca todos los contratos de la base de datos
	 * @return List<Contrato> 
	 */
	public List<Contrato> buscarTodos();
	
	/**
	 * Busca todos los contratos relacionados con el cliente dado
	 * @param cliente
	 * @return List<Contrato> 
	 */
	public List<Contrato> buscarPorCliente(final Cliente cliente);
	
	/**
	 * Busca todos los contratos que tienen la fecha de vigencia dada
	 * @param fechaDeVigencia
	 * @return List<Contrato> 
	 */
	public List<Contrato> buscarPorFechaDeVigencia(final Date fechaDeVigencia);
	
	/**
	 * Busca todos los contratos que tienen la fecha de caducidad dada
	 * @param fechaDeCaducidad
	 * @return List<Contrato> 
	 */
	public List<Contrato> buscarPorFechaDeCaducidad(final Date fechaDeCaducidad);
	
	/**
	 * Busca todos los contratos que cuestan el precio dado
	 * @param precioMensual
	 * @return List<Contrato> 
	 */
	public List<Contrato> buscarPorPrecioMensual(final float precioMensual);
	
	/**
	 * Busca todos los contratos que cuestan el precio dado y cuyo cliente tiene un nombre especifico
	 * @param nombre
	 * @param precio
	 * @return List<Contrato> 
	 */
	public List<Contrato> buscarPorNombreDeClienteYPrecio(String nombre, float precio);
}