package es.uma.informatica.sii.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 * Entity implementation class for Entity: Matricula
 *
 */
@Entity

@IdClass(MatriculaPK.class)
public class Matricula implements Serializable {

	   
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((curso == null) ? 0 : curso.hashCode());
		result = prime * result + ((expediente == null) ? 0 : expediente.hashCode());
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
		Matricula other = (Matricula) obj;
		if (curso == null) {
			if (other.curso != null)
				return false;
		} else if (!curso.equals(other.curso))
			return false;
		if (expediente == null) {
			if (other.expediente != null)
				return false;
		} else if (!expediente.equals(other.expediente))
			return false;
		return true;
	}

	@Id
	@Column(length=10, nullable=false)
	private String curso;
	@Column(length=9, nullable=false)
	private Integer número_archivo;
	@Column(nullable=false)
	private Boolean activa;
	@Column(length=6)
	private String turno;
	@Column(length=10)
	private String tipo_estudio;
	private Date fecha_matricula;
	@Column(length=1)
	private boolean nuevo_ingreso;
	private String listado_asignaturas;   
	@Id
	@Column(nullable=false)
	private Expediente expediente;

	private static final long serialVersionUID = 1L;

	public Matricula() {
		super();
	}   
	public String getCurso() {
		return this.curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}   
	public Integer getNúmero_archivo() {
		return this.número_archivo;
	}

	public void setNúmero_archivo(Integer número_archivo) {
		this.número_archivo = número_archivo;
	}   
	public Boolean getActiva() {
		return this.activa;
	}

	public void setActiva(Boolean activa) {
		this.activa = activa;
	}   
	public String getTurno() {
		return this.turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}   
	public String getTipo_estudio() {
		return this.tipo_estudio;
	}

	public void setTipo_estudio(String tipo_estudio) {
		this.tipo_estudio = tipo_estudio;
	}   
	public Date getFecha_matricula() {
		return this.fecha_matricula;
	}

	public void setFecha_matricula(Date fecha_matricula) {
		this.fecha_matricula = fecha_matricula;
	}   
	public boolean getNuevo_ingreso() {
		return this.nuevo_ingreso;
	}

	public void setNuevo_ingreso(boolean nuevo_ingreso) {
		this.nuevo_ingreso = nuevo_ingreso;
	}   
	public String getListado_asignaturas() {
		return this.listado_asignaturas;
	}

	public void setListado_asignaturas(String listado_asignaturas) {
		this.listado_asignaturas = listado_asignaturas;
	}   
		
	public Expediente getExpedienteNum() {
		return this.expediente;
	}

	public void setExpedienteNum(Expediente expediente_número) {
		this.expediente = expediente_número;
	}
   
}
