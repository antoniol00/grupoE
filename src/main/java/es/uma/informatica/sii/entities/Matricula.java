package es.uma.informatica.sii.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	@Column(length=9, nullable=false, unique=true)
	private Integer numero_archivo;
	@Column(nullable=false)
	private Boolean activa;
	@Column(length=6)
	private String turno;
	@Column(length=10)
	private String tipo_estudio;
	@Temporal(TemporalType.DATE)
	private Date fecha_matricula;
	@Column(length=1)
	private boolean nuevo_ingreso;
	private String listado_asignaturas;   
	@Id
	@ManyToOne
	private Expediente expediente;
	@OneToMany(mappedBy="matricula")
	private List<Asigna_grupos> asigna_grupos;

	private static final long serialVersionUID = 1L;

	public Matricula() {
		super();
	}   
	
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public Integer getNumero_archivo() {
		return numero_archivo;
	}
	public void setNumero_archivo(Integer numero_archivo) {
		this.numero_archivo = numero_archivo;
	}
	public Boolean getActiva() {
		return activa;
	}
	public void setActiva(Boolean activa) {
		this.activa = activa;
	}
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}
	public String getTipo_estudio() {
		return tipo_estudio;
	}
	public void setTipo_estudio(String tipo_estudio) {
		this.tipo_estudio = tipo_estudio;
	}
	public Date getFecha_matricula() {
		return fecha_matricula;
	}
	public void setFecha_matricula(Date fecha_matricula) {
		this.fecha_matricula = fecha_matricula;
	}
	public boolean isNuevo_ingreso() {
		return nuevo_ingreso;
	}
	public void setNuevo_ingreso(boolean nuevo_ingreso) {
		this.nuevo_ingreso = nuevo_ingreso;
	}
	public String getListado_asignaturas() {
		return listado_asignaturas;
	}
	public void setListado_asignaturas(String listado_asignaturas) {
		this.listado_asignaturas = listado_asignaturas;
	}
	public Expediente getExpediente() {
		return expediente;
	}
	public void setExpediente(Expediente expediente) {
		this.expediente = expediente;
	}
	public List<Asigna_grupos> getAsigna_grupos() {
		return asigna_grupos;
	}
	public void setAsigna_grupos(List<Asigna_grupos> asigna_grupos) {
		this.asigna_grupos = asigna_grupos;
	}
	@Override
	public String toString() {
		return "Matricula [curso=" + curso + ", expediente=" + expediente + "]";
	}
   
}
