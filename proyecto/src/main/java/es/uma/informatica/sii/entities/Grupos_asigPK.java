package es.uma.informatica.sii.entities;

import java.io.Serializable;
import java.lang.String;

/**
 * ID class for entity: Grupos_asig
 *
 */ 
public class Grupos_asigPK  implements Serializable {   
   
	         
	private String curso;         
	private String grupo;         
	private Integer asignatura;
	private static final long serialVersionUID = 1L;

	public Grupos_asigPK() {}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public Integer getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Integer asignatura) {
		this.asignatura = asignatura;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((asignatura == null) ? 0 : asignatura.hashCode());
		result = prime * result + ((curso == null) ? 0 : curso.hashCode());
		result = prime * result + ((grupo == null) ? 0 : grupo.hashCode());
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
		Grupos_asigPK other = (Grupos_asigPK) obj;
		if (asignatura == null) {
			if (other.asignatura != null)
				return false;
		} else if (!asignatura.equals(other.asignatura))
			return false;
		if (curso == null) {
			if (other.curso != null)
				return false;
		} else if (!curso.equals(other.curso))
			return false;
		if (grupo == null) {
			if (other.grupo != null)
				return false;
		} else if (!grupo.equals(other.grupo))
			return false;
		return true;
	}

	

	
   
}
