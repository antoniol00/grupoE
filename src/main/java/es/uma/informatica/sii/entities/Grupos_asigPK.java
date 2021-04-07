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
	private Asignatura asignatura_preferencia;
	private static final long serialVersionUID = 1L;

	public Grupos_asigPK() {}

	

	public String getCurso() {
		return this.curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}
	

	public String getGrupo_id() {
		return this.grupo_id;
	}

	public void setGrupo_id(String grupo_id) {
		this.grupo_id = grupo_id;
	}
	

	public Asignatura getAsignatura_preferencia() {
		return this.asignatura_preferencia;
	}

	public void setAsignatura_preferencia(Asignatura asignatura_preferencia) {
		this.asignatura_preferencia = asignatura_preferencia;
	}
	
   
	/*
	 * @see java.lang.Object#equals(Object)
	 */	
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof Grupos_asigPK)) {
			return false;
		}
		Grupos_asigPK other = (Grupos_asigPK) o;
		return true
			&& (getCurso() == null ? other.getCurso() == null : getCurso().equals(other.getCurso()))
			&& (getGrupo_id() == null ? other.getGrupo_id() == null : getGrupo_id().equals(other.getGrupo_id()))
			&& (getAsignatura_preferencia() == null ? other.getAsignatura_preferencia() == null : getAsignatura_preferencia().equals(other.getAsignatura_preferencia()));
	}
	
	/*	 
	 * @see java.lang.Object#hashCode()
	 */	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (getCurso() == null ? 0 : getCurso().hashCode());
		result = prime * result + (getGrupo_id() == null ? 0 : getGrupo_id().hashCode());
		result = prime * result + (getAsignatura_preferencia() == null ? 0 : getAsignatura_preferencia().hashCode());
		return result;
	}
   
   
}
