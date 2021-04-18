package es.uma.informatica.sii.entities;

import java.io.Serializable;

public class Asigna_gruposPK implements Serializable {
	private static final long serialVersionUID = 1L;
	private AsignaturaPK asignatura;
	private MatriculaPK matricula;
	
	public Asigna_gruposPK() {}

	public AsignaturaPK getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(AsignaturaPK asignatura) {
		this.asignatura = asignatura;
	}

	public MatriculaPK getMatricula() {
		return matricula;
	}

	public void setMatricula(MatriculaPK matricula) {
		this.matricula = matricula;
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
		if (!(obj instanceof Asigna_gruposPK))
			return false;
		Asigna_gruposPK other = (Asigna_gruposPK) obj;
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

	
}
