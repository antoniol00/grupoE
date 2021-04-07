package es.uma.informatica.sii.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity

public class Titulacion implements Serializable{
	@Id
	@Column(nullable = false)
	private Integer codigo;
	@Column(nullable = false)
	private String nombre;
	@Column(nullable = false)
	private Integer creditos;
	
	public Titulacion() {
		super();
	}
	
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	public Integer getCodigo() {
		return this.codigo;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setCreditos(Integer creditos) {
		this.creditos = creditos;
	}
	
	public Integer getCreditos() {
		return this.creditos;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Titulacion))
			return false;
		Titulacion other = (Titulacion) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}
