package es.uma.informatica.sii.entities;

import java.io.Serializable;

/**
 * ID class for entity: Asignatura
 *
 */ 
public class AsignaturaPK  implements Serializable {   
   
	         
	private Integer referencia;         
	private Integer titulacion;
	private static final long serialVersionUID = 1L;

	public AsignaturaPK() {}

	public Integer getReferencia() {
		return referencia;
	}

	public void setReferencia(Integer referencia) {
		this.referencia = referencia;
	}

	public Integer getTitulacion() {
		return titulacion;
	}

	public void setTitulacion(Integer titulacion) {
		this.titulacion = titulacion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((referencia == null) ? 0 : referencia.hashCode());
		result = prime * result + ((titulacion == null) ? 0 : titulacion.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof AsignaturaPK))
			return false;
		AsignaturaPK other = (AsignaturaPK) obj;
		if (referencia == null) {
			if (other.referencia != null)
				return false;
		} else if (!referencia.equals(other.referencia))
			return false;
		if (titulacion == null) {
			if (other.titulacion != null)
				return false;
		} else if (!titulacion.equals(other.titulacion))
			return false;
		return true;
	}

}
