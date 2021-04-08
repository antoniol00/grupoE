package es.uma.informatica.sii.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Expediente
 *
 */
@Entity

public class Expediente implements Serializable {
 
	@Id
	private Integer numero;
	private Boolean activo;
	private Double nota_media;
	private Integer creditos_fb;
	private Integer creditos_ob;
	private Integer creditos_pe;
	private Integer creditos_tf;
	
	@ManyToOne
	@Column(nullable=false)
	private Titulacion titulacion;
	
	@ManyToOne
	@Column(nullable=false)
	private Alumno alumno;
	
	@OneToMany(mappedBy="expediente")
	private List<Encuesta> encuestas;
	
	@OneToMany(mappedBy="expediente")
	private List<Matricula> matriculas;
	
	private static final long serialVersionUID = 1L;

	public Expediente() {
		super();
	}   
	public Integer getNumero() {
		return this.numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}   
	public Boolean getActivo() {
		return this.activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}   
	public Double getNota_media() {
		return this.nota_media;
	}

	public void setNota_media(Double nota_media) {
		this.nota_media = nota_media;
	}   
	public Integer getCreditos_fb() {
		return this.creditos_fb;
	}

	public void setCreditos_fb(Integer creditos_fb) {
		this.creditos_fb = creditos_fb;
	}   
	public Integer getCreditos_ob() {
		return this.creditos_ob;
	}

	public void setCreditos_ob(Integer creditos_ob) {
		this.creditos_ob = creditos_ob;
	}   
	public Integer getCreditos_pe() {
		return this.creditos_pe;
	}

	public void setCreditos_pe(Integer creditos_pe) {
		this.creditos_pe = creditos_pe;
	}   
	public Integer getCreditos_tf() {
		return this.creditos_tf;
	}

	public void setCreditos_tf(Integer creditos_tf) {
		this.creditos_tf = creditos_tf;
	}   
	public Titulacion getTitulacion_codigo() {
		return this.titulacion;
	}

	public void setTitulacion_codigo(Titulacion titulacion_codigo) {
		this.titulacion = titulacion_codigo;
	}   
	public Alumno getAlumno_dni() {
		return this.alumno;
	}

	public void setAlumno_dni(Alumno alumno_dni) {
		this.alumno = alumno_dni;
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
   
}
