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
		super();
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alumno == null) ? 0 : alumno.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof PeticionPK))
			return false;
		PeticionPK other = (PeticionPK) obj;
		if (alumno == null) {
			if (other.alumno != null)
				return false;
		} else if (!alumno.equals(other.alumno))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PeticionPK [date=" + date + ", alumno=" + alumno + "]";
	}

}
