package es.uma.informatica.sii.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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
	private Titulacion titulacion_codigo;
	private static final long serialVersionUID = 1L;

	public Asignatura() {
		super();
	}

	public Integer getReferencia() {
		return this.referencia;
	}

	public void setReferencia(Integer referencia) {
		this.referencia = referencia;
	}

	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Double getCreditos_teoricos() {
		return this.creditos_teoricos;
	}

	public void setCreditos_teoricos(Double creditos_teoricos) {
		this.creditos_teoricos = creditos_teoricos;
	}

	public Double getCreditos_practicos() {
		return this.creditos_practicos;
	}

	public void setCreditos_practicos(Double creditos_practicos) {
		this.creditos_practicos = creditos_practicos;
	}

	public Boolean getOfertada() {
		return this.ofertada;
	}

	public void setOfertada(Boolean ofertada) {
		this.ofertada = ofertada;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCurso() {
		return this.curso;
	}

	public void setCurso(Integer curso) {
		this.curso = curso;
	}

	public String getCaracter() {
		return this.caracter;
	}

	public void setCaracter(String caracter) {
		this.caracter = caracter;
	}

	public Integer getDuracion() {
		return this.duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	public Integer getCuatrimestre() {
		return this.cuatrimestre;
	}

	public void setCuatrimestre(Integer cuatrimestre) {
		this.cuatrimestre = cuatrimestre;
	}

	public String getIdiomas() {
		return this.idiomas;
	}

	public void setIdiomas(String idiomas) {
		this.idiomas = idiomas;
	}

	public Integer getTitulacion_codigo() {
		return this.titulacion_codigo;
	}

	public void setTitulacion_codigo(Integer titulacion_codigo) {
		this.titulacion_codigo = titulacion_codigo;
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

}
