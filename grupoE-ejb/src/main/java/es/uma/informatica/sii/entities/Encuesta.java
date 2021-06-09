package es.uma.informatica.sii.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity implementation class for Entity: Encuesta
 *
 */
@Entity

@IdClass(EncuestaPK.class)
public class Encuesta implements Serializable {

	@Id
	@Column(nullable = false, name = "FECHA_ENVIO")
	@Temporal(TemporalType.DATE)

	private Date fecha_envio;

	@ManyToOne
	@Id
	@JoinColumn(name = "EXPEDIENTE")
	private Expediente expediente;

	private static final long serialVersionUID = 1L;

	public Encuesta() {
		super();
	}

	public Date getFecha_envio() {
		return fecha_envio;
	}

	public void setFecha_envio(Date fecha_envio) {
		this.fecha_envio = fecha_envio;
	}

	public Expediente getExpediente() {
		return expediente;
	}

	public void setExpediente(Expediente expediente) {
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Encuesta other = (Encuesta) obj;
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
		return "Encuesta [fecha_envio=" + fecha_envio + ", expediente=" + expediente + "]";
	}

}
