package es.uma.informatica.sii.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Encuesta
 *
 */
@Entity

@IdClass(EncuestaPK.class)
public class Encuesta implements Serializable {

	@Id
	@Column(nullable=false, name="FECHA_ENVIO")
	@Temporal(TemporalType.DATE)
	
	private Date fecha_envio;  
	
	@ManyToOne
	@Id
	@JoinColumn(name="EXPEDIENTE")
	private Expediente expediente;

	@ManyToMany (mappedBy="encuestas")
	private List<Grupos_asig> asigYGrupo;
	
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
	public Expediente getExpedienteNum() {
		return this.expediente;
	}

	public void setExpedienteNum(Expediente expediente_número) {
		this.expediente = expediente_número;
	}
	public Expediente getExpediente() {
		return expediente;
	}
	public void setExpediente(Expediente expediente) {
		this.expediente = expediente;
	}
	public List<Grupos_asig> getAsigYGrupo() {
		return asigYGrupo;
	}
	public void setAsigYGrupo(List<Grupos_asig> asigYGrupo) {
		this.asigYGrupo = asigYGrupo;
	}
	@Override
	public String toString() {
		return "Encuesta [fecha_envio=" + fecha_envio + ", expediente=" + expediente + "]";
	}
   
}