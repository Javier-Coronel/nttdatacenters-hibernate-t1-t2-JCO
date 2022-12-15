package com.nttdata.hibernate.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import com.nttdata.hibernate.persistence.Cliente;
import com.nttdata.hibernate.persistence.Contrato;
import com.nttdata.hibernate.persistence.ContratoDaoI;
import com.nttdata.hibernate.persistence.ContratoDaoImpl;
/**
 * 
 * Implementación del servicio de contratos
 * @author jcoro
 *
 */
public class ContratoManagementServiceImpl implements ContratoManagementServiceI {
	
	private ContratoDaoI contratoDao;
	
	/**
	 * Metodo constructor
	 * @param session
	 */
	public ContratoManagementServiceImpl(final Session session) {
		this.contratoDao = new ContratoDaoImpl(session);
	}
	
	@Override
	public void insertContrato(Contrato insertedContrato) {
		if(insertedContrato != null && insertedContrato.getContratoId() == null) {
			contratoDao.insert(insertedContrato);
		}

	}

	@Override
	public void updateContrato(Contrato updatedContrato) {
		if(updatedContrato != null && updatedContrato.getContratoId() != null) {
			contratoDao.update(updatedContrato);
		}
	}

	@Override
	public void deleteContrato(Contrato deletedContrato) {
		if(deletedContrato != null && deletedContrato.getContratoId() != null) {
			contratoDao.delete(deletedContrato);
		}

	}

	@Override
	public Contrato searchById(Long id) {
		return (id!=null)?contratoDao.searchById(id):null;
	}

	@Override
	public List<Contrato> buscarTodos() {
		return contratoDao.searchAll();
	}

	@Override
	public List<Contrato> buscarPorCliente(Cliente cliente) {
		
		List<Contrato> listaContratos = new ArrayList<>();
		if(cliente!=null) {
			listaContratos = contratoDao.buscarPorCliente(cliente);
		}
		return listaContratos;
	}

	@Override
	public List<Contrato> buscarPorFechaDeVigencia(Date fechaDeVigencia) {

		List<Contrato> listaContratos = new ArrayList<>();
		if(fechaDeVigencia!=null) {
			listaContratos = contratoDao.buscarPorFechaDeVigencia(fechaDeVigencia);
		}
		return listaContratos;
	}

	@Override
	public List<Contrato> buscarPorFechaDeCaducidad(Date fechaDeCaducidad) {

		List<Contrato> listaContratos = new ArrayList<>();
		if(fechaDeCaducidad!=null) {
			listaContratos = contratoDao.buscarPorFechaDeVigencia(fechaDeCaducidad);
		}
		return listaContratos;
	}

	@Override
	public List<Contrato> buscarPorPrecioMensual(float precioMensual) {
		
		List<Contrato> listaContratos = new ArrayList<>();
		if((!Float.isNaN(precioMensual))&&Float.isFinite(precioMensual)) {
			listaContratos = contratoDao.buscarPorPrecioMensual(precioMensual);
		}
		return listaContratos;
	}

	@Override
	public List<Contrato> buscarPorNombreDeClienteYPrecio(String nombre,float precio) {
		
		List<Contrato> listaContratos = new ArrayList<>();
		if(StringUtils.isNotBlank(nombre)&&(!Float.isNaN(precio))&&Float.isFinite(precio)) {
			listaContratos = contratoDao.buscarPorNombreDeClienteYPrecio(nombre,precio);
		}
		return listaContratos;
	}

}
