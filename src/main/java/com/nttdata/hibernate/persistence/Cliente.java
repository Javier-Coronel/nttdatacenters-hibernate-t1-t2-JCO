package com.nttdata.hibernate.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


/**
 * La entidad que representa la tabla cliente
 * @author jcoro
 */
@Entity
@Table(name = "Cliente")
public class Cliente extends AbstractEntity implements Serializable{

	/**	Serial version */
	private static final long serialVersionUID = 1L;
	
	/**	Clave Primaria */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CLIENTE_ID")
	private Long clienteId;
	
	/**	Nombre del cliente */
	@Column(name = "NOMBRE_CLIENTE",nullable=false)
	private String nombre;
	
	/**	Primer apellido del cliente */
	@Column(name = "APELLIDO_UNO_CLIENTE",nullable=false)
	private String apellidoUno;
	
	/**	Segundo apellido del cliente */
	@Column(name = "APELLIDO_DOS_CLIENTE")
	private String apellidoDos;
	
	/**	Documento de identidad del cliente */
	@Column(name = "IDENTIDAD_CLIENTE",unique=true,nullable=false,length=9)
	private String documentoIdentidad;
	/** Lista de contratos */
	@OneToMany(mappedBy = "cliente")
	private List<Contrato> contratos;
	
	/**
	 * 
	 * @return el id del cliente
	 */
	public Long getClienteId() {
		return clienteId;
	}
	
	/**
	 * 
	 * @param clienteId
	 */
	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}
	
	/**
	 * 
	 * @return el nombre del cliente
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * 
	 * @return el primer apellido del cliente
	 */
	
	public String getApellidoUno() {
		return apellidoUno;
	}

	/**
	 * 
	 * @param apellidoUno
	 */
	public void setApellidoUno(String apellidoUno) {
		this.apellidoUno = apellidoUno;
	}

	/**
	 * 
	 * @return el segundo apellido del cliente
	 */
	public String getApellidoDos() {
		return apellidoDos;
	}

	/**
	 * 
	 * @param apellidoDos
	 */
	public void setApellidoDos(String apellidoDos) {
		this.apellidoDos = apellidoDos;
	}
	
	/**
	 * 
	 * @return el documento de identidad del cliente
	 */
	public String getDocumentoIdentidad() {
		return documentoIdentidad;
	}

	/**
	 * 
	 * @param documentoIdentidad
	 */
	public void setDocumentoIdentidad(String documentoIdentidad) {
		this.documentoIdentidad = documentoIdentidad;
	}
	
	/**
	 * @return los contratos del cliente
	 */
	public List<Contrato> getContratos(){
		return contratos;
	}
	
	/**
	 * 
	 * @param contratosList
	 */
	public void setContratos(List<Contrato> contratosList) {
		this.contratos = contratosList;
	}
	
	@Override
	public String toString() {
		return "Cliente [clienteId=" + clienteId 
				+ ", nombre=" + nombre 
				+ ", apellidoUno=" + apellidoUno
				+ ", apellidoDos=" + apellidoDos 
				+ ", documentoIdentidad=" + documentoIdentidad + "]";
	}

	@Transient
	@Override
	public Long getId() {
		return this.clienteId;
	}


	@Override
	public void setId(Long id) {
		this.clienteId = id;
	}
	
}
