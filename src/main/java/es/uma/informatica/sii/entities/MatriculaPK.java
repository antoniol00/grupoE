package es.uma.informatica.sii.entities;

import java.io.Serializable;

/**
 * ID class for entity: Matricula
 *
 */
public class MatriculaPK implements Serializable {

	private String curso;
	private Integer expediente;
	private static final long serialVersionUID = 1L;

	public MatriculaPK() {
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
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
		result = prime * result + ((curso == null) ? 0 : curso.hashCode());
		result = prime * result + ((expediente == null) ? 0 : expediente.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof MatriculaPK))
			return false;
		MatriculaPK other = (MatriculaPK) obj;
		if (curso == null) {
			if (other.curso != null)
				return false;
		} else if (!curso.equals(other.curso))
			return false;
		if (expediente == null) {
			if (other.expediente != null)
				return false;
		} else if (!expediente.equals(other.expediente))
			return false;
		return true;
	}

	

}
