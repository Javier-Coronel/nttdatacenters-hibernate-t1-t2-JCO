package com.nttdata.hibernate.persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * La entidad que representa la tabla contrato
 * @author jcoro
 *
 */
@Entity
@Table(name = "Contrato")
public class Contrato extends AbstractEntity implements Serializable{
	
	/**	Serial version */
	private static final long serialVersionUID = 1L;
	
	/**	Clave Primaria */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CONTRATO_ID")
	private Long contratoId;
	
	/** Fecha de vigencia del concrato */
	@Column(name = "FECHA_VIGENCIA", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaDeVigencia;
	
	/** Fecha de caducidad del concrato */
	@Column(name = "FECHA_CADUCIDAD", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaDeCaducidad;
	
	/** Precio mensual del contrato */
	@Column(name = "PRECIO_MENSUAL", nullable = false)
	@Digits(integer = 20, fraction = 2)
	private float precioMensual;
	
	/** Cliente con el que el contrato esta relacionado */
	@ManyToOne
	@JoinColumn(name="CLIENTE_ID")
	private Cliente cliente;
	
	
	/**
	 * 
	 * @return el id del contrato
	 */
	public Long getContratoId() {
		return contratoId;
	}
	
	/**
	 * 
	 * @param contratoId
	 */
	public void setContratoId(Long contratoId) {
		this.contratoId = contratoId;
	}
	
	/**
	 * 
	 * @return la fecha de vigencia del contrato
	 */
	public Date getFechaDeVigencia() {
		return fechaDeVigencia;
	}
	
	/**
	 * 
	 * @param fechaDeVigencia
	 */
	public void setFechaDeVigencia(Date fechaDeVigencia) {
		this.fechaDeVigencia = fechaDeVigencia;
	}
	
	/**
	 * 
	 * @return la fecha de caducidad del contrato
	 */
	public Date getFechaDeCaducidad() {
		return fechaDeCaducidad;
	}
	
	/**
	 * 
	 * @param fechaDeCaducidad
	 */
	public void setFechaDeCaducidad(Date fechaDeCaducidad) {
		this.fechaDeCaducidad = fechaDeCaducidad;
	}
	
	/**
	 * 
	 * @return el precio mensual del contrato
	 */
	public float getPrecioMensual() {
		return precioMensual;
	}
	
	/**
	 * 
	 * @param precioMensual
	 */
	public void setPrecioMensual(float precioMensual) {
		this.precioMensual = precioMensual;
	}
	
	/**
	 * 
	 * @return el cliente relacionado con el contrato
	 */
	public Cliente getCliente() {
		return cliente;
	}
	
	/**
	 * 
	 * @param cliente
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	@Override
	public String toString() {
		return "Contrato [contratoId=" + contratoId 
				+ ", fechaDeVigencia=" + fechaDeVigencia 
				+ ", fechaDeCaducidad=" + fechaDeCaducidad 
				+ ", precioMensual=" + precioMensual 
				+ ", cliente=" + cliente + "]";
	}
	
	@Transient
	@Override
	public Long getId() {
		return this.contratoId;
	}

	@Override
	public void setId(Long id) {
		this.contratoId = id;
		
	}
}
