package es.uma.informatica.sii.entities;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Double;
import java.lang.Integer;
import java.lang.String;
import java.sql.Date;
import javax.persistence.*;

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
		result = prime * result + ((expediente_número == null) ? 0 : expediente_número.hashCode());
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
		if (expediente_número == null) {
			if (other.expediente_número != null)
				return false;
		} else if (!expediente_número.equals(other.expediente_número))
			return false;
		return true;
	}

	@Id
	private String curso;
	private Integer número_archivo;
	private Boolean activa;
	private String turno;
	private String tipo_estudio;
	private Date fecha_matricula;
	private boolean nuevo_ingreso;
	private String listado_asignaturas;   
	@Id
	private Double expediente_número;
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
	public Double getExpediente_número() {
		return this.expediente_número;
	}

	public void setExpediente_número(Double expediente_número) {
		this.expediente_número = expediente_número;
	}
   
}
