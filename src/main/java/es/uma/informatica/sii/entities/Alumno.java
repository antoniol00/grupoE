package es.uma.informatica.sii.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Alumno
 *
 */
@Entity

public class Alumno implements Serializable {

	@Id
	@Column(length=9)
	private String dni;
	@Column(nullable=false)
	private String nombre_completo;
	@Column(nullable=false)
	private String email_institucional;
	private String email_personal;
	@Column(length=9)
	private Integer telefono_fijo;
	@Column(length=9)
	private Integer telefono_movil;
	private String direccion_notificacion;
	@Column(length=64)
	private String localidad_notificacion;
	@Column(length=32)
	private String provincia_notificacion;
	@Column(length=5)
	private Integer codigo_postal;
	
	@OneToMany(mappedBy="alumno")
	private List<Expediente> expedientes;
	
	private static final long serialVersionUID = 1L;

	public Alumno() {
		super();
	}   
	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}   
	public String getNombre_completo() {
		return this.nombre_completo;
	}

	public void setNombre_completo(String nombre_completo) {
		this.nombre_completo = nombre_completo;
	}   
	public String getEmail_institucional() {
		return this.email_institucional;
	}

	public void setEmail_institucional(String email_institucional) {
		this.email_institucional = email_institucional;
	}   
	public String getEmail_personal() {
		return this.email_personal;
	}

	public void setEmail_personal(String email_personal) {
		this.email_personal = email_personal;
	}   
	public Integer getTelefono_fijo() {
		return this.telefono_fijo;
	}

	public void setTelefono_fijo(Integer telefono_fijo) {
		this.telefono_fijo = telefono_fijo;
	}   
	public Integer getTelefono_movil() {
		return this.telefono_movil;
	}

	public void setTelefono_movil(Integer telefono_movil) {
		this.telefono_movil = telefono_movil;
	}   
	public String getDireccion_notificacion() {
		return this.direccion_notificacion;
	}

	public void setDireccion_notificacion(String direccion_notificacion) {
		this.direccion_notificacion = direccion_notificacion;
	}   
	public String getLocalidad_notificacion() {
		return this.localidad_notificacion;
	}

	public void setLocalidad_notificacion(String localidad_notificacion) {
		this.localidad_notificacion = localidad_notificacion;
	}   
	public String getProvincia_notificacion() {
		return this.provincia_notificacion;
	}

	public void setProvincia_notificacion(String provincia_notificacion) {
		this.provincia_notificacion = provincia_notificacion;
	}   
	public Integer getCodigo_postal() {
		return this.codigo_postal;
	}

	public void setCodigo_postal(Integer codigo_postal) {
		this.codigo_postal = codigo_postal;
	}
	
	public List<Expediente> getExpedientes() {
		return expedientes;
	}
	public void setExpedientes(List<Expediente> expedientes) {
		this.expedientes = expedientes;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Alumno))
			return false;
		Alumno other = (Alumno) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Alumno [dni=" + dni + ", nombre_completo=" + nombre_completo + ", email_institucional="
				+ email_institucional + "]";
	}
	
   
}
