package es.uma.informatica.sii.entities;

import java.io.Serializable;
import java.util.Date;

/**
 * ID class for entity: Peticion
 *
 */
public class PeticionPK implements Serializable {

	private Date date;
	private String alumno;
	private static final long serialVersionUID = 1L;

	public PeticionPK() {
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAlumno() {
		return this.alumno;
	}

	public void setAlumno(String alumno) {
		this.alumno = alumno;
	}

	/*
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof PeticionPK)) {
			return false;
		}
		PeticionPK other = (PeticionPK) o;
		return true && (getDate() == null ? other.getDate() == null : getDate().equals(other.getDate()))
				&& (getAlumno() == null ? other.getAlumno() == null : getAlumno().equals(other.getAlumno()));
	}

	/*
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (getDate() == null ? 0 : getDate().hashCode());
		result = prime * result + (getAlumno() == null ? 0 : getAlumno().hashCode());
		return result;
	}

}
