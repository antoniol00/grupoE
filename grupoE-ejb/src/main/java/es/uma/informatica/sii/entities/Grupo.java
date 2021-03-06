package es.uma.informatica.sii.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Grupo
 *
 */
@Entity

public class Grupo implements Serializable {

	@Id
	@Column(nullable = false, length = 10)
	private String id;
	@Column(length = 1, nullable = false)
	private Integer curso;
	@Column(length = 1, nullable = false)
	private String letra;
	@Column(length = 6, nullable = false)
	private String turno;
	@Column(length = 2, nullable = false)
	private Boolean ingles;
	private Boolean visible;
	@Column(length = 3)
	private Integer plazas;
	private String asignar;

	@OneToMany(mappedBy = "grupoReflexivo")
	private List<Grupo> grupos;

	@ManyToOne
	private Grupo grupoReflexivo;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Titulacion titulacion;

	@OneToMany(mappedBy = "grupo")
	private List<Grupos_asig> gruposAsig;

	@OneToMany(mappedBy = "grupo")
	private List<Asigna_grupos> asignagrupos;

	@OneToMany(mappedBy = "grupo")
	private List<Clase> clases;

	private static final long serialVersionUID = 1L;

	public Grupo() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getCurso() {
		return curso;
	}

	public void setCurso(Integer curso) {
		this.curso = curso;
	}

	public String getLetra() {
		return letra;
	}

	public void setLetra(String letra) {
		this.letra = letra;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public Boolean getIngles() {
		return ingles;
	}

	public void setIngles(Boolean ingles) {
		this.ingles = ingles;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Integer getPlazas() {
		return plazas;
	}

	public void setPlazas(Integer plazas) {
		this.plazas = plazas;
	}

	public String getAsignar() {
		return asignar;
	}

	public void setAsignar(String asignar) {
		this.asignar = asignar;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public Grupo getGrupoReflexivo() {
		return grupoReflexivo;
	}

	public void setGrupoReflexivo(Grupo grupo) {
		this.grupoReflexivo = grupo;
	}

	public Titulacion getTitulacion() {
		return titulacion;
	}

	public void setTitulacion(Titulacion titulacion) {
		this.titulacion = titulacion;
	}

	public List<Grupos_asig> getGruposAsig() {
		return gruposAsig;
	}

	public void setGruposAsig(List<Grupos_asig> gruposAsig) {
		this.gruposAsig = gruposAsig;
	}

	public List<Asigna_grupos> getAsignagrupos() {
		return asignagrupos;
	}

	public void setAsignagrupos(List<Asigna_grupos> asignagrupos) {
		this.asignagrupos = asignagrupos;
	}

	public List<Clase> getClases() {
		return clases;
	}

	public void setClases(List<Clase> clases) {
		this.clases = clases;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Grupo other = (Grupo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Grupo [id=" + id + ", curso=" + curso + ", letra=" + letra + "]";
	}

}
