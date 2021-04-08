package es.uma.informatica.sii.entities;

import java.io.Serializable;
import java.lang.String;

/**
 * ID class for entity: Grupos_asig
 *
 */ 
public class Grupos_asigPK  implements Serializable {   
   
	         
	private String curso;         
	private String grupo_id;         
	private Integer asignatura_referencia;
	private static final long serialVersionUID = 1L;

	public Grupos_asigPK() {}

	

	public String getCurso() {
		return curso;
	}



	public void setCurso(String curso) {
		this.curso = curso;
	}



	public String getGrupo_id() {
		return grupo_id;
	}



	public void setGrupo_id(String grupo_id) {
		this.grupo_id = grupo_id;
	}



	public Integer getAsignatura_referencia() {
		return asignatura_referencia;
	}



	public void setAsignatura_referencia(Integer asignatura_referencia) {
		this.asignatura_referencia = asignatura_referencia;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((asignatura_referencia == null) ? 0 : asignatura_referencia.hashCode());
		result = prime * result + ((curso == null) ? 0 : curso.hashCode());
		result = prime * result + ((grupo_id == null) ? 0 : grupo_id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Grupos_asigPK))
			return false;
		Grupos_asigPK other = (Grupos_asigPK) obj;
		if (asignatura_referencia == null) {
			if (other.asignatura_referencia != null)
				return false;
		} else if (!asignatura_referencia.equals(other.asignatura_referencia))
			return false;
		if (curso == null) {
			if (other.curso != null)
				return false;
		} else if (!curso.equals(other.curso))
			return false;
		if (grupo_id == null) {
			if (other.grupo_id != null)
				return false;
		} else if (!grupo_id.equals(other.grupo_id))
			return false;
		return true;
	}
	
	
   
   
}
