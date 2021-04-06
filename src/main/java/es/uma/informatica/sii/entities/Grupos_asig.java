package es.uma.informatica.sii.entities;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Grupos_asig
 *
 */
@Entity

@IdClass(Grupos_asigPK.class)
public class Grupos_asig implements Serializable {

	   
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((asignatura_preferencia == null) ? 0 : asignatura_preferencia.hashCode());
		result = prime * result + ((curso == null) ? 0 : curso.hashCode());
		result = prime * result + ((grupo_id == null) ? 0 : grupo_id.hashCode());
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
		Grupos_asig other = (Grupos_asig) obj;
		if (asignatura_preferencia == null) {
			if (other.asignatura_preferencia != null)
				return false;
		} else if (!asignatura_preferencia.equals(other.asignatura_preferencia))
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

	@Id
	@Column(nullable=false)
	private String curso;
	private String oferta;   
	@Id
	@Column(nullable=false)
	private String grupo_id;   
	@Id
	@Column(nullable=false)
	private Integer asignatura_preferencia;
	private static final long serialVersionUID = 1L;

	public Grupos_asig() {
		super();
	}   
	public String getCurso() {
		return this.curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}   
	public String getOferta() {
		return this.oferta;
	}

	public void setOferta(String oferta) {
		this.oferta = oferta;
	}   
	public String getGrupo_id() {
		return this.grupo_id;
	}

	public void setGrupo_id(String grupo_id) {
		this.grupo_id = grupo_id;
	}   
	public Integer getAsignatura_preferencia() {
		return this.asignatura_preferencia;
	}

	public void setAsignatura_preferencia(Integer asignatura_preferencia) {
		this.asignatura_preferencia = asignatura_preferencia;
	}
   
}
