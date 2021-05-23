package es.uma.informatica.sii.entities;

import java.io.Serializable;
import java.util.Date;

/**
 * ID class for entity: Encuesta
 *
 */
public class EncuestaPK implements Serializable {

	private Date fecha_envio;
	private Integer expediente;
	private static final long serialVersionUID = 1L;

	public EncuestaPK() {
		super();
	}

	public Date getFecha_envio() {
		return fecha_envio;
	}

	public void setFecha_envio(Date fecha_envio) {
		this.fecha_envio = fecha_envio;
	}

	public Integer getExpediente() {
		return expediente;
	}

	public void setExpediente(Integer expediente) {
		this.expediente = expediente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((expediente == null) ? 0 : expediente.hashCode());
		result = prime * result + ((fecha_envio == null) ? 0 : fecha_envio.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof EncuestaPK))
			return false;
		EncuestaPK other = (EncuestaPK) obj;
		if (expediente == null) {
			if (other.expediente != null)
				return false;
		} else if (!expediente.equals(other.expediente))
			return false;
		if (fecha_envio == null) {
			if (other.fecha_envio != null)
				return false;
		} else if (!fecha_envio.equals(other.fecha_envio))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EncuestaPK [fecha_envio=" + fecha_envio + ", expediente=" + expediente + "]";
	}

}
