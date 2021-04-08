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
 * Entity implementation class for Entity: Asignatura
 *
 */
@Entity

public class Asignatura implements Serializable {

	@Id
	private Integer referencia;
	@Column(nullable = false)
	private Integer codigo;
	@Column(nullable = false)
	private Double creditos_teoricos;
	@Column(nullable = false)
	private Double creditos_practicos;
	@Column(nullable = false)
	private Boolean ofertada;
	@Column(nullable = false)
	private String nombre;
	private Integer curso;
	private String caracter;
	private Integer duracion;
	private Integer cuatrimestre;
	private String idiomas;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Titulacion titulacion;

	@OneToMany(mappedBy = "asignatura")
	private List<Clase> clases_asignadas;

	@OneToMany(mappedBy = "asignatura")
	private List<Asigna_grupos> asign_grupos;

	@OneToMany(mappedBy = "asignatura_referencia")
	private List<Grupos_asig> grupos;

	private static final long serialVersionUID = 1L;

	public Asignatura() {
		super();
	}

	public Integer getReferencia() {
		return referencia;
	}

	public void setReferencia(Integer referencia) {
		this.referencia = referencia;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Double getCreditos_teoricos() {
		return creditos_teoricos;
	}

	public void setCreditos_teoricos(Double creditos_teoricos) {
		this.creditos_teoricos = creditos_teoricos;
	}

	public Double getCreditos_practicos() {
		return creditos_practicos;
	}

	public void setCreditos_practicos(Double creditos_practicos) {
		this.creditos_practicos = creditos_practicos;
	}

	public Boolean getOfertada() {
		return ofertada;
	}

	public void setOfertada(Boolean ofertada) {
		this.ofertada = ofertada;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCurso() {
		return curso;
	}

	public void setCurso(Integer curso) {
		this.curso = curso;
	}

	public String getCaracter() {
		return caracter;
	}

	public void setCaracter(String caracter) {
		this.caracter = caracter;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	public Integer getCuatrimestre() {
		return cuatrimestre;
	}

	public void setCuatrimestre(Integer cuatrimestre) {
		this.cuatrimestre = cuatrimestre;
	}

	public String getIdiomas() {
		return idiomas;
	}

	public void setIdiomas(String idiomas) {
		this.idiomas = idiomas;
	}

	public Titulacion getTitulacion() {
		return titulacion;
	}

	public void setTitulacion(Titulacion titulacion) {
		this.titulacion = titulacion;
	}

	public List<Clase> getClases_asignadas() {
		return clases_asignadas;
	}

	public void setClases_asignadas(List<Clase> clases_asignadas) {
		this.clases_asignadas = clases_asignadas;
	}

	public List<Asigna_grupos> getAsign_grupos() {
		return asign_grupos;
	}

	public void setAsign_grupos(List<Asigna_grupos> asign_grupos) {
		this.asign_grupos = asign_grupos;
	}

	public List<Grupos_asig> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupos_asig> grupos) {
		this.grupos = grupos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((referencia == null) ? 0 : referencia.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Asignatura))
			return false;
		Asignatura other = (Asignatura) obj;
		if (referencia == null) {
			if (other.referencia != null)
				return false;
		} else if (!referencia.equals(other.referencia))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Asignatura [referencia=" + referencia + ", codigo=" + codigo + ", nombre=" + nombre + "]";
	}

}
