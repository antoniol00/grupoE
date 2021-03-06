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
	@ManyToOne
	private Grupo grupo;

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

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((asignatura == null) ? 0 : asignatura.hashCode());
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
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Asigna_grupos [asignatura=" + asignatura + ", matricula=" + matricula
				+ ", grupo_id=" + grupo + "]";
	}

}
