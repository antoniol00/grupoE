package es.uma.informatica.sii.entities;

import java.io.Serializable;
import java.lang.Double;
import java.sql.Date;

/**
 * ID class for entity: Encuesta
 *
 */ 
public class EncuestaPK  implements Serializable {   
   
	         
	private Date fecha_envio;         
	private Double expediente_numero;
	private static final long serialVersionUID = 1L;

	public EncuestaPK() {}

	

	public Date getFecha_envio() {
		return this.fecha_envio;
	}

	public void setFecha_envio(Date fecha_envio) {
		this.fecha_envio = fecha_envio;
	}
	

	public Double getExpediente_numero() {
		return this.expediente_numero;
	}

	public void setExpediente_numero(Double expediente_numero) {
		this.expediente_numero = expediente_numero;
	}
	
   
	/*
	 * @see java.lang.Object#equals(Object)
	 */	
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof EncuestaPK)) {
			return false;
		}
		EncuestaPK other = (EncuestaPK) o;
		return true
			&& (getFecha_envio() == null ? other.getFecha_envio() == null : getFecha_envio().equals(other.getFecha_envio()))
			&& (getExpediente_numero() == null ? other.getExpediente_numero() == null : getExpediente_numero().equals(other.getExpediente_numero()));
	}
	
	/*	 
	 * @see java.lang.Object#hashCode()
	 */	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (getFecha_envio() == null ? 0 : getFecha_envio().hashCode());
		result = prime * result + (getExpediente_numero() == null ? 0 : getExpediente_numero().hashCode());
		return result;
	}
   
   
}
