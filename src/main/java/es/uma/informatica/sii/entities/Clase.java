package es.uma.informatica.sii.entities;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity

public class Clase implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(nullable = false)
	private Date Dia;
	@Id
	@Column(nullable = false)
	private Time Hora_inicio;
	private Time Hora_fin;

	public Clase() {
		super();
	}

	public Date getDia() {
		return Dia;
	}

	public void setDia(Date dia) {
		Dia = dia;
	}

	public Time getHora_inicio() {
		return Hora_inicio;
	}

	public void setHora_inicio(Time hora_inicio) {
		Hora_inicio = hora_inicio;
	}

	public Time getHora_fin() {
		return Hora_fin;
	}

	public void setHora_fin(Time hora_fin) {
		Hora_fin = hora_fin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Dia == null) ? 0 : Dia.hashCode());
		result = prime * result + ((Hora_fin == null) ? 0 : Hora_fin.hashCode());
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
		Clase other = (Clase) obj;
		if (Dia == null) {
			if (other.Dia != null)
				return false;
		} else if (!Dia.equals(other.Dia))
			return false;
		if (Hora_fin == null) {
			if (other.Hora_fin != null)
				return false;
		} else if (!Hora_fin.equals(other.Hora_fin))
			return false;
		return true;
	}

}
