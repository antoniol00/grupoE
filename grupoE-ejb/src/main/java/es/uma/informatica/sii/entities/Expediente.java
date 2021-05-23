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
 * Entity implementation class for Entity: Expediente
 *
 */
@Entity

public class Expediente implements Serializable {

	@Id
	@Column(length = 9)
	private Integer numero;
	private Boolean activo;
	private Double nota_media;
	private Double creditos_fb;
	private Double creditos_ob;
	private Double creditos_pe;
	private Double creditos_tf;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Titulacion titulacion;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Alumno alumno;

	@OneToMany(mappedBy = "expediente")
	private List<Encuesta> encuestas;

	@OneToMany(mappedBy = "expediente")
	private List<Matricula> matriculas;

	private static final long serialVersionUID = 1L;

	public Expediente() {
		super();
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Double getNota_media() {
		return nota_media;
	}

	public void setNota_media(Double nota_media) {
		this.nota_media = nota_media;
	}

	public Double getCreditos_fb() {
		return creditos_fb;
	}

	public void setCreditos_fb(Double creditos_fb) {
		this.creditos_fb = creditos_fb;
	}

	public Double getCreditos_ob() {
		return creditos_ob;
	}

	public void setCreditos_ob(Double creditos_ob) {
		this.creditos_ob = creditos_ob;
	}

	public Double getCreditos_pe() {
		return creditos_pe;
	}

	public void setCreditos_pe(Double creditos_pe) {
		this.creditos_pe = creditos_pe;
	}

	public Double getCreditos_tf() {
		return creditos_tf;
	}

	public void setCreditos_tf(Double creditos_tf) {
		this.creditos_tf = creditos_tf;
	}

	public Titulacion getTitulacion() {
		return titulacion;
	}

	public void setTitulacion(Titulacion titulacion) {
		this.titulacion = titulacion;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public List<Encuesta> getEncuestas() {
		return encuestas;
	}

	public void setEncuestas(List<Encuesta> encuestas) {
		this.encuestas = encuestas;
	}

	public List<Matricula> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Expediente))
			return false;
		Expediente other = (Expediente) obj;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Expediente [numero=" + numero + ", alumno=" + alumno + "]";
	}

}
