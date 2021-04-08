package es.uma.informatica.sii.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

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

	@Id
	@Column(nullable=false,length=5)
	private String curso;
	@Column(length=10)
	private String oferta;   
	@Id
	@ManyToOne
	@JoinColumn(nullable=false)
	private Grupo grupo;   
	@Id
	@ManyToOne
	@JoinColumn(nullable=false)
	private Asignatura asignatura;
	
	@ManyToMany
	@JoinTable(name="encuesta_grupos",joinColumns= {
			@JoinColumn(name="CURSO",referencedColumnName="curso"),
			@JoinColumn(name="grupo",referencedColumnName="grupo_id"),
			@JoinColumn(name="asignatura",referencedColumnName="asignatura_referencia")
	},
	inverseJoinColumns= {
			@JoinColumn(name="fecha_envio",referencedColumnName="fecha_envio"),
			@JoinColumn(name="expediente",referencedColumnName="expediente")
	})
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
	public Grupo getGrupo_id() {
		return grupo;
	}
	public void setGrupo_id(Grupo grupo_id) {
		this.grupo = grupo_id;
	}
	public Asignatura getAsignatura_referencia() {
		return asignatura;
	}
	public void setAsignatura_referencia(Asignatura asignatura) {
		this.asignatura = asignatura;
	}
	public List<Encuesta> getEncuestas() {
		return encuestas;
	}
	public void setEncuestas(List<Encuesta> encuestas) {
		this.encuestas = encuestas;
	}
	@Override
	public String toString() {
		return "Grupos_asig [curso=" + curso + ", grupo_id=" + grupo + "]";
	}
   
}
