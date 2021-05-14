package es.uma.informatica.sii.entities;

import java.io.Serializable;

/**
 * ID class for entity: Asignatura
 *
 */ 
public class AsignaturaPK  implements Serializable {   
   
	         
	private Integer codigo;         
	private Integer titulacion;
	private static final long serialVersionUID = 1L;

	public AsignaturaPK() {}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer referencia) {
		this.codigo = referencia;
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
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (titulacion == null) {
			if (other.titulacion != null)
				return false;
		} else if (!titulacion.equals(other.titulacion))
			return false;
		return true;
	}

}
