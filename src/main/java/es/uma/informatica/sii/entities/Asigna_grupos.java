package es.uma.informatica.sii.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

/**
 * Entity implementation class for Entity: Asigna_grupos
 *
 */
@Entity
@IdClass(Asigna_gruposPK.class)
public class Asigna_grupos implements Serializable {

	@Id
	@ManyToOne
	private Asignatura asignatura;
	@Id
	@ManyToOne
	private Matricula matricula;
	@Id
	private Expediente expediente;
	@ManyToOne
	private Grupo grupo_id;

	private static final long serialVersionUID = 1L;

	public Asigna_grupos() {
		super();
	}

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	public Expediente getExpediente() {
		return expediente;
	}

	public void setExpediente(Expediente expediente) {
		this.expediente = expediente;
	}

	public Grupo getGrupo_id() {
		return grupo_id;
	}

	public void setGrupo_id(Grupo grupo_id) {
		this.grupo_id = grupo_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((asignatura == null) ? 0 : asignatura.hashCode());
		result = prime * result + ((expediente == null) ? 0 : expediente.hashCode());
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Asigna_grupos))
			return false;
		Asigna_grupos other = (Asigna_grupos) obj;
		if (asignatura == null) {
			if (other.asignatura != null)
				return false;
		} else if (!asignatura.equals(other.asignatura))
			return false;
		if (expediente == null) {
			if (other.expediente != null)
				return false;
		} else if (!expediente.equals(other.expediente))
			return false;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Asigna_grupos [asignatura=" + asignatura + ", matricula=" + matricula + ", expediente=" + expediente
				+ ", grupo_id=" + grupo_id + "]";
	}

}
