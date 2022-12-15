package com.nttdata.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.hibernate.Session;
import com.nttdata.hibernate.persistence.*;
import com.nttdata.hibernate.services.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
 * Clase principal
 * 
 * @author jcoro
 *
 */
public class NTTDataMain {
	
	
	private static final Logger LOG = LoggerFactory.getLogger(NTTDataMain.class);
	static final Session session = NTTDataHibernateUtil.getSessionFactory().openSession();
	
	// Declaracion de servicios
	
	private static final ClienteManagementServiceI clienteService = new ClienteManagementServiceImpl(session);
	private static final ContratoManagementServiceI contratoService = new ContratoManagementServiceImpl(session);
	
	// Declaracion de Clientes
	
	private static final Cliente clienteA = new Cliente();
	private static final Cliente clienteB = new Cliente();
	private static final Cliente clienteC = new Cliente();
	
	static String nombreJavier = "Javier";
	static String apellidoCoronel = "Coronel";
	static String apellidoOrtiz = "Ortiz";
	static String documentoIdentidadDeC = "123456787";
	
	// Declaracion de Contratos
	
	static final Contrato contrato1 = new Contrato();
	static final Contrato contrato2 = new Contrato();
	static final Contrato contrato3 = new Contrato();
	static final Contrato contrato4 = new Contrato();
	static final Contrato contrato5 = new Contrato();
	
	/**
	 * Método principal
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Inserta varios clientes y contratos.
		
		insercionDeClientesYContratos();
		
		// Busca todos los clientes.
		
		buscarTodosLosClientes();
		
		// Busca los clientes con un nombre y apellido dados.
		
		buscaPorNombreJavierYApellidoCoronel();
		
		// Busca los clientes con un nombre y apellidos dados.
		
		buscaPorNombreJavierPrimerApellidoCoronelYSegundoApellidoOrtiz();
		
		// Busca un cliente con un documento de identificacion especifico.
		
		buscarPorIdentificacion(documentoIdentidadDeC);
		
		// Actualiza un cliente y muestra que se ha eliminado.
		
		clienteC.setNombre("Antonio");
		clienteService.updateCliente(clienteC);
		buscarPorIdentificacion(documentoIdentidadDeC);
		
		// Elimina un cliente y muestra que se ha eliminado.
		
		clienteService.deleteCliente(clienteA);
		buscarTodosLosClientes(); 
		
		// Busca un cliente mediante su Id.
		
		clienteBuscarPorId(2L);
		
		// Busca todos los contratos que hallan sido introducidos en la base de datos
		
		buscarTodosLosContratos();
		
		// Busca todos los contratos que sean del cliente C
		
		buscarPorCliente(clienteC);
		
		// Busca todos los contratos que cuesten 23 y el nombre de su cliente sea Javier
		
		buscarPorNombreDeClienteYPrecio(23L);
		
		// Lista los contratos que cuesten 14
		
		buscarPorPrecio(14L);
		
		// Busca los contratos cuya fecha de vigencia sea el 20 de diciembre de 2019 a la 1 
		
		buscarPorFechaDeVigencia(1576800000000L);
		
		// Busca los contratos cuya fecha de caducidad sea el 20 de diciembre de 2019 a la 1
		
		buscarPorFechaDeCaducidad(1576800000000L);
		
		// Se busca el contrato 1 con su id
		
		contratoBuscarPorId(contrato1.getContratoId());
		
		// Se actualiza el contrato 1 y se comprueba
		
		contrato1.setPrecioMensual(3);
		contratoService.updateContrato(contrato1);
		contratoBuscarPorId(contrato1.getContratoId());
		
		
		
		session.close();
	}

	/**
	 * Busca un contrato por su id
	 * @param id
	 */
	private static void contratoBuscarPorId(Long id) {
		final Contrato contratoBuscadoPorId = contratoService.searchById(id);
		if(LOG.isDebugEnabled())LOG.debug("Busqueda de los contratos por fecha de caducidad: Id {}, fecha de vigencia {}, fecha de caducidad {}, precio {}, cliente{}",contratoBuscadoPorId.getContratoId(),contratoBuscadoPorId.getFechaDeVigencia(),contratoBuscadoPorId.getFechaDeCaducidad(),contratoBuscadoPorId.getPrecioMensual(),contratoBuscadoPorId.getCliente());
	}

	/**
	 * Busca los contratos cuya fecha de caducidad sea la dada y se guada en una traza cado uno.
	 * @param fecha La fecha a buscar
	 */
	private static void buscarPorFechaDeCaducidad(Long fecha) {
		final List<Contrato> listaContratosPorFechaDeCaducidad = contratoService.buscarPorFechaDeCaducidad(new Date(fecha));
		for(Contrato contrato : listaContratosPorFechaDeCaducidad) {
			if(LOG.isDebugEnabled())LOG.debug("Busqueda de los contratos por fecha de caducidad: Id {}, fecha de vigencia {}, fecha de caducidad {}, precio {}, cliente",contrato.getContratoId(),contrato.getFechaDeVigencia(),contrato.getFechaDeCaducidad(),contrato.getPrecioMensual());
		}
	}

	/**
	 * Busca los contratos cuya fecha de vigencia sea la dada y se guada en una traza cado uno.
	 * @param fecha La fecha a buscar
	 */
	private static void buscarPorFechaDeVigencia(Long fecha) {
		final List<Contrato> listaContratosPorFechaDeVigencia = contratoService.buscarPorFechaDeVigencia(new Date(fecha));
		for(Contrato contrato : listaContratosPorFechaDeVigencia) {
			if(LOG.isDebugEnabled())LOG.debug("Busqueda de los contratos por fecha de vigencia: Id {}, fecha de vigencia {}, fecha de caducidad {}, precio {}, cliente",contrato.getContratoId(),contrato.getFechaDeVigencia(),contrato.getFechaDeCaducidad(),contrato.getPrecioMensual());
		}
	}

	/**
	 * Busca los contratos cuyo precio mensual sea el dado y se guada en una traza cado uno.
	 * @param precio El precio a buscar
	 */
	private static void buscarPorPrecio(float precio) {
		final List<Contrato> listaContratosPorPrecioMensual = contratoService.buscarPorPrecioMensual(precio);
		for(Contrato contrato : listaContratosPorPrecioMensual) {
			if(LOG.isDebugEnabled())LOG.debug("Busqueda de los contratos por precio: Id {}, fecha de vigencia {}, fecha de caducidad {}, precio {}, cliente",contrato.getContratoId(),contrato.getFechaDeVigencia(),contrato.getFechaDeCaducidad(),contrato.getPrecioMensual());
		}
	}

	/**
	 * Busca los contratos cuyo precio mensual sea el dado y tenga relaccion con un cliente con el nombre Javier y se guada en una traza cado uno.
	 * @param precio El precio a buscar
	 */
	private static void buscarPorNombreDeClienteYPrecio(Long precio) {
		final List<Contrato> listaContratosPorNombreDelClienteYPrecio = contratoService.buscarPorNombreDeClienteYPrecio(nombreJavier, precio);
		for(Contrato contrato : listaContratosPorNombreDelClienteYPrecio) {
			if(LOG.isDebugEnabled())LOG.debug("Busqueda de los contratos por cliente y por precio: Id {}, fecha de vigencia {}, fecha de caducidad {}, precio {}, cliente",contrato.getContratoId(),contrato.getFechaDeVigencia(),contrato.getFechaDeCaducidad(),contrato.getPrecioMensual());
		}
	}
	
	/**
	 * Busca los contratos del cliente dado y se guada en una traza cado uno.
	 * @param cliente El cliente con el que esta relacionado los contratos a buscar
	 */
	private static void buscarPorCliente(Cliente cliente) {
		final List<Contrato> listaContratosPorCliente = contratoService.buscarPorCliente(cliente);
		for(Contrato contrato : listaContratosPorCliente) {
			if(LOG.isDebugEnabled())LOG.debug("Busqueda de los contratos del cliente c: Id {}, fecha de vigencia {}, fecha de caducidad {}, precio {}, cliente",contrato.getContratoId(),contrato.getFechaDeVigencia(),contrato.getFechaDeCaducidad(),contrato.getPrecioMensual());
		}
	}

	/**
	 * Busca el cliente que tenga el id dado y se guada en una traza.
	 * @param id El id del cliente a buscar
	 */
	private static void clienteBuscarPorId(Long id) {
		final Cliente clienteBuscadoPorId = clienteService.searchById(id);
		if(LOG.isDebugEnabled())LOG.debug("Busqueda por id: Id {}, nombre {}, primer apellido {}, segundo apellido {}, documento de identidad {}.",clienteBuscadoPorId.getClienteId(), clienteBuscadoPorId.getNombre(), clienteBuscadoPorId.getApellidoUno(), clienteBuscadoPorId.getApellidoDos(), clienteBuscadoPorId.getDocumentoIdentidad());
	}

	/**
	 * Busca los clientes que tengan el nombre Javier, el primer apellido Coronel y el segundo Ortiz y se guada en una traza cado uno.
	 */
	private static void buscaPorNombreJavierPrimerApellidoCoronelYSegundoApellidoOrtiz() {
		final List<Cliente> listaClienteConNombreYApellidos = clienteService.buscarPorNombreYApellidos(nombreJavier,apellidoCoronel,apellidoOrtiz);
		for (Cliente cliente : listaClienteConNombreYApellidos) {
			if(LOG.isDebugEnabled())LOG.debug("Busqueda por nombre y apellidos: Id {}, nombre {}, primer apellido {}, segundo apellido {}, documento de identidad {}.",cliente.getClienteId(), cliente.getNombre(), cliente.getApellidoUno(), cliente.getApellidoDos(), cliente.getDocumentoIdentidad());
		}
	}
	
	/**
	 * Busca los clientes que tengan el nombre Javier y el apellido Coronel y se guada en una traza cado uno.
	 */
	private static void buscaPorNombreJavierYApellidoCoronel() {
		final List<Cliente> listaClienteConNombreYApellido = clienteService.buscarPorNombreYApellidoUno(nombreJavier,apellidoCoronel);
		for (Cliente cliente : listaClienteConNombreYApellido) {
			if(LOG.isDebugEnabled())LOG.debug("Busqueda desde nombre y primer apellido: Id {}, nombre {}, primer apellido {}, segundo apellido {}, documento de identidad {}.",cliente.getClienteId(), cliente.getNombre(), cliente.getApellidoUno(), cliente.getApellidoDos(), cliente.getDocumentoIdentidad());
		}
	}
	
	/**
	 * Busca todos los contratos y se guarda en una traza cado uno.
	 */
	private static void buscarTodosLosContratos() {
		final List<Contrato> listaTodosContratos = contratoService.buscarTodos();
		for(Contrato contrato : listaTodosContratos) {
			if(LOG.isDebugEnabled())LOG.debug("Busqueda de todos los contratos: Id {}, fecha de vigencia {}, fecha de caducidad {}, precio {}, cliente",contrato.getContratoId(),contrato.getFechaDeVigencia(),contrato.getFechaDeCaducidad(),contrato.getPrecioMensual());
		}
	}
	
	/**
	 * Busca un cliente mediante un documento de identificación especifico y
	 * se guarda en una traza.
	 * @param identificacion La identificación del cliente a buscar.
	 */
	public static void buscarPorIdentificacion(String identificacion) {
		final Cliente cliente = clienteService.buscarPorDocumentoIdentificacion(identificacion);
		if(LOG.isDebugEnabled())LOG.debug("Busqueda por documento de identificacion: Id {}, nombre {}, primer apellido {}, segundo apellido {}, documento de identidad {}.",cliente.getClienteId(), cliente.getNombre(), cliente.getApellidoUno(), cliente.getApellidoDos(), cliente.getDocumentoIdentidad());
	}

	/**
	 * Busca todos los clientes y se guada en una traza cado uno.
	 */
	public static void buscarTodosLosClientes() {
		List<Cliente> listaCliente = clienteService.buscarTodos();
		for (Cliente cliente : listaCliente) {
			if(LOG.isDebugEnabled())LOG.debug("Busqueda de todos los clientes: Id {}, nombre {}, primer apellido {}, segundo apellido {}, documento de identidad {}.",cliente.getClienteId(), cliente.getNombre(), cliente.getApellidoUno(), cliente.getApellidoDos(), cliente.getDocumentoIdentidad());
		}
	}
	
	/**
	 * Añade valores a varios clientes y contratos y los inserta en la base de datos.
	 */
	private static void insercionDeClientesYContratos() {
		
		// Se añade a los clientes valores
		
		clienteA.setNombre(nombreJavier);
		clienteA.setApellidoUno(apellidoCoronel);
		clienteA.setApellidoDos(apellidoOrtiz);
		clienteA.setDocumentoIdentidad("123456789");
		clienteA.setUpdatedUser("");
		
		clienteB.setNombre(nombreJavier);
		clienteB.setApellidoUno(apellidoOrtiz);
		clienteB.setApellidoDos(apellidoCoronel);
		clienteB.setDocumentoIdentidad("123456788");
		clienteB.setUpdatedUser("");
		
		clienteC.setNombre(nombreJavier);
		clienteC.setApellidoUno(apellidoCoronel);
		clienteC.setApellidoDos(apellidoOrtiz);
		clienteC.setDocumentoIdentidad(documentoIdentidadDeC);
		clienteC.setUpdatedUser("");
		
		// Se añade a los contratos valores
		
		contrato1.setFechaDeVigencia(new Date(1576800000000L));
		contrato1.setFechaDeCaducidad(new Date());
		contrato1.setPrecioMensual(23L);
		contrato1.setCliente(clienteB);
		
		contrato2.setFechaDeVigencia(new Date(1576800000000L));
		contrato2.setFechaDeCaducidad(new Date(1576800000000L));
		contrato2.setPrecioMensual(23L);
		contrato2.setCliente(clienteB);
		
		contrato3.setFechaDeVigencia(new Date(1576800000000L));
		contrato3.setFechaDeCaducidad(new Date(1576800000000L));
		contrato3.setPrecioMensual(16L);
		contrato3.setCliente(clienteB);
		
		contrato4.setFechaDeVigencia(new Date(1576800000000L));
		contrato4.setFechaDeCaducidad(new Date());
		contrato4.setPrecioMensual(30L);
		contrato4.setCliente(clienteC);
		
		contrato5.setFechaDeVigencia(new Date());
		contrato5.setFechaDeCaducidad(new Date(1576800000000L));
		contrato5.setPrecioMensual(5L);
		contrato5.setCliente(clienteC);

		// Creacion de listas de contratos
		
		final List<Contrato> contratosClienteB = new ArrayList<>();
		contratosClienteB.add(contrato1);
		contratosClienteB.add(contrato2);
		contratosClienteB.add(contrato3);
		
		final List<Contrato> contratosClienteC = new ArrayList<>();
		contratosClienteC.add(contrato4);
		contratosClienteC.add(contrato5);

		clienteB.setContratos(contratosClienteB);
		clienteC.setContratos(contratosClienteC);

		// Insercion de clientes
		
		clienteService.insertCliente(clienteA);
		clienteService.insertCliente(clienteB);
		clienteService.insertCliente(clienteC);
		
		// Insercion de contratos
		
		contratoService.insertContrato(contrato1);
		contratoService.insertContrato(contrato2);
		contratoService.insertContrato(contrato3);
		contratoService.insertContrato(contrato4);
		contratoService.insertContrato(contrato5);
	}
	
}