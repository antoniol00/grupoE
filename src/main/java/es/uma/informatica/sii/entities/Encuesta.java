package es.uma.informatica.sii.entities;

import java.io.Serializable;
import java.lang.Double;
import java.sql.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Encuesta
 *
 */
@Entity

@IdClass(EncuestaPK.class)
public class Encuesta implements Serializable {

	   
	@Id
	@Column(nullable=false)
	private Date fecha_envio;   
	@Id
	@Column(nullable=false)
	private Double expediente_numero;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((expediente_numero == null) ? 0 : expediente_numero.hashCode());
		result = prime * result + ((fecha_envio == null) ? 0 : fecha_envio.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Encuesta other = (Encuesta) obj;
		if (expediente_numero == null) {
			if (other.expediente_numero != null)
				return false;
		} else if (!expediente_numero.equals(other.expediente_numero))
			return false;
		if (fecha_envio == null) {
			if (other.fecha_envio != null)
				return false;
		} else if (!fecha_envio.equals(other.fecha_envio))
			return false;
		return true;
	}

	private static final long serialVersionUID = 1L;

	public Encuesta() {
		super();
	}   
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
   
}
