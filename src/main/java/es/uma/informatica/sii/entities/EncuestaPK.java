package es.uma.informatica.sii.entities;

import java.io.Serializable;
import java.sql.Date;

/**
 * ID class for entity: Encuesta
 *
 */ 
public class EncuestaPK  implements Serializable {   
   
	         
	private Date fecha_envio;         
	private Expediente expediente_número;
	private static final long serialVersionUID = 1L;

	public EncuestaPK() {}

	

	public Date getFecha_envio() {
		return this.fecha_envio;
	}

	public void setFecha_envio(Date fecha_envio) {
		this.fecha_envio = fecha_envio;
	}
	

	public Expediente getExpedienteNum() {
		return this.expediente_número;
	}

	public void setExpedienteNum(Expediente expediente_número) {
		this.expediente_número = expediente_número;
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
			&& (getExpedienteNum() == null ? other.getExpedienteNum() == null : getExpedienteNum().equals(other.getExpedienteNum()));
	}
	
	/*	 
	 * @see java.lang.Object#hashCode()
	 */	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (getFecha_envio() == null ? 0 : getFecha_envio().hashCode());
		result = prime * result + (getExpedienteNum() == null ? 0 : getExpedienteNum().hashCode());
		return result;
	}
   
   
}
