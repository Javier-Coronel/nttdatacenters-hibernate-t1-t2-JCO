package com.nttdata.hibernate.services;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import com.nttdata.hibernate.persistence.Cliente;
import com.nttdata.hibernate.persistence.ClienteDaoI;
import com.nttdata.hibernate.persistence.ClienteDaoImpl;

public class ClienteManagementServiceImpl implements ClienteManagementServiceI{
	
	private ClienteDaoI clienteDao;
	
	
	/**
	 * Metodo constructor
	 * @param session
	 */
	public ClienteManagementServiceImpl(final Session session) {
		this.clienteDao = new ClienteDaoImpl(session);
	}

	@Override
	public void insertCliente(final Cliente insertedCliente) {
		if(insertedCliente != null && insertedCliente.getClienteId() == null && insertedCliente.getDocumentoIdentidad().length() == 9) {
			clienteDao.insert(insertedCliente);
		}
		
	}

	@Override
	public void updateCliente(final Cliente updatedCliente) {
		if(updatedCliente != null && updatedCliente.getClienteId() != null && updatedCliente.getDocumentoIdentidad().length() == 9) {
			clienteDao.update(updatedCliente);
		}
	}

	@Override
	public void deleteCliente(final Cliente deletedCliente) {
		if(deletedCliente != null && deletedCliente.getClienteId() != null && deletedCliente.getDocumentoIdentidad().length() == 9) {
			clienteDao.delete(deletedCliente);
		}
	}

	@Override
	public Cliente searchById(final Long id) {
		
		Cliente cliente = null;
		
		if (id != null) {
			cliente = clienteDao.searchById(id);
		}
		return cliente;
	}

	@Override
	public List<Cliente> buscarTodos() {
		return clienteDao.searchAll();
	}

	@Override
	public List<Cliente> buscarPorNombreYApellidoUno(String nombre, String apellidoUno) {
		List<Cliente> listaClientes = new ArrayList<>();
		if(StringUtils.isNotBlank(nombre) && StringUtils.isNotBlank(apellidoUno)) {
			listaClientes = clienteDao.buscarPorNombreYApellidoUno(nombre, apellidoUno);
		}
		return listaClientes;
	}

	@Override
	public List<Cliente> buscarPorNombreYApellidos(String nombre, String apellidoUno, String apellidoDos) {
		List<Cliente> listaClientes = new ArrayList<>();
		if(StringUtils.isNotBlank(nombre) && StringUtils.isNotBlank(apellidoUno) && StringUtils.isNotBlank(apellidoDos)) {
			listaClientes = clienteDao.buscarPorNombreYApellidos(nombre, apellidoUno, apellidoDos);
		}
		return listaClientes;
	}

	@Override
	public Cliente buscarPorDocumentoIdentificacion(final String documentoIdentidad) {
		Cliente clientes = null;
		if(StringUtils.isNotBlank(documentoIdentidad)) {
			clientes = clienteDao.buscarPorDocumentoIdentificacion(documentoIdentidad);
		}
		return clientes;
	}

}
