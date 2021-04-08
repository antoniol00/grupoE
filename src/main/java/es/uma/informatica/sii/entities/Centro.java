package es.uma.informatica.sii.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity

public class Centro implements Serializable{
	
	@Id
	private String nombre;
	private String direccion;
	private Integer tlf_conserjeria;
	private static final long serialVersionUID = 1L;
	
	@ManyToMany
	@JoinTable(name="titul_centro",joinColumns=@JoinColumn(name="centro_fk"),
	inverseJoinColumns=@JoinColumn(name="titulacion_fk"))
	private List<Titulacion> titulaciones;
	
	public Centro() {
		super();
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getDireccion() {
		return this.direccion;
	}
	
	public void setTlf_conserjeria(Integer tlf_conserjeria) {
		this.tlf_conserjeria = tlf_conserjeria;
	}
	
	public Integer getTlf_conserjeria() {
		return this.tlf_conserjeria;
	}
	
	public List<Titulacion> getTitulaciones() {
		return titulaciones;
	}

	public void setTitulaciones(List<Titulacion> titulaciones) {
		this.titulaciones = titulaciones;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Centro))
			return false;
		Centro other = (Centro) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
}
