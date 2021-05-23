package es.uma.informatica.sii.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 * Entity implementation class for Entity: Grupos_asig
 *
 */
@Entity

@IdClass(Grupos_asigPK.class)
public class Grupos_asig implements Serializable {

	@Id
	@Column(length = 5)
	private String curso;
	@Column(length = 10)
	private String oferta;
	@Id
	@ManyToOne
	private Grupo grupo;
	@Id
	@ManyToOne
	private Asignatura asignatura;

	@ManyToMany
	@JoinTable(name = "ENCUESTA_GRUPOS", joinColumns = { @JoinColumn(name = "CURSO", referencedColumnName = "curso"),
			@JoinColumn(name = "GRUPO", referencedColumnName = "grupo_id"),
			@JoinColumn(name = "ASIGNATURA", referencedColumnName = "asignatura_codigo"),
			@JoinColumn(name = "TITULACION", referencedColumnName = "asignatura_titulacion_codigo") }, inverseJoinColumns = {
					@JoinColumn(name = "FECHA_ENVIO", referencedColumnName = "fecha_envio"),
					@JoinColumn(name = "EXPEDIENTE", referencedColumnName = "expediente") })
	private List<Encuesta> encuestas;

	private static final long serialVersionUID = 1L;

	public Grupos_asig() {
		super();
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getOferta() {
		return oferta;
	}

	public void setOferta(String oferta) {
		this.oferta = oferta;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	public List<Encuesta> getEncuestas() {
		return encuestas;
	}

	public void setEncuestas(List<Encuesta> encuestas) {
		this.encuestas = encuestas;
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
		if (!(obj instanceof Grupos_asig))
			return false;
		Grupos_asig other = (Grupos_asig) obj;
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

	@Override
	public String toString() {
		return "Grupos_asig [curso=" + curso + ", oferta=" + oferta + ", grupo=" + grupo + ", asignatura=" + asignatura
				+ ", encuestas=" + encuestas + "]";
	}

}
