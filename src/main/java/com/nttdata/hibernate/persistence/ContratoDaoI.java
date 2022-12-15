package com.nttdata.hibernate.persistence;

import java.util.Date;
import java.util.List;
/**
 * Interface del DAO de la tabla Contrato
 * @author jcoro
 *
 */
public interface ContratoDaoI extends CommonDaoI<Contrato>{
	
	/**
	 * Devuelve los contratos que tengan una fecha de vigencia especifica
	 * @param fechaDeVigencia
	 * @return List<Contrato>
	 */
	public List<Contrato> buscarPorFechaDeVigencia(final Date fechaDeVigencia);
	
	/**
	 * Devuelve los contratos que tengan una fecha de caducidad especifica
	 * @param fechaDeCaducidad
	 * @return List<Contrato>
	 */
	public List<Contrato> buscarPorFechaDeCaducidad(final Date fechaDeCaducidad);
	
	/**
	 * Devuelve los contratos que cuesten mensualmente lo mismo que el parametro dado
	 * @param precioMensual
	 * @return List<Contrato>
	 */
	public List<Contrato> buscarPorPrecioMensual(final float precioMensual);
	
	/**
	 * Devuelve los contratos en los que el cliente tenga un nombre especifico y que cuesten mensualmente lo mismo que el segundo parametro dado
	 * @param contrato
	 * @param precio
	 * @return List<Contrato>
	 */
	public List<Contrato> buscarPorNombreDeClienteYPrecio(final String nombre, final float precio);
	
	/**
	 * Devuelve los contratos que tenga un cliente
	 * @param cliente
	 * @return List<Contrato>
	 */
	public List<Contrato> buscarPorCliente(final Cliente cliente);
	
}
