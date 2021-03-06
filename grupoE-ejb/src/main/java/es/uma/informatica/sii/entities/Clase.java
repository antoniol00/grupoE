package es.uma.informatica.sii.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Entity implementation class for Entity: Clase
 *
 */
@Entity

@IdClass(ClasePK.class)
public class Clase implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(nullable = false)
	private String dia;
	@Id
	@Column(nullable = false)
	private String hora_inicio;
	@Column(nullable = false)
	private String hora_fin;
	@Id
	@ManyToOne
	private Grupo grupo;
	@JoinColumn(name = "ASIGNATURA_CODIGO", referencedColumnName = "CODIGO", nullable = false)
	@JoinColumn(name = "TITULACION_CODIGO", referencedColumnName = "TITULACION_CODIGO", nullable = false)
	@ManyToOne
	private Asignatura asignatura;

	public Clase() {
		super();
	}

	public String getDia() {
		return this.dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getHora_inicio() {
		return this.hora_inicio;
	}

	public void setHora_inicio(String hora_inicio) {
		this.hora_inicio = hora_inicio;
	}

	public String getHora_fin() {
		return this.hora_fin;
	}

	public void setHora_fin(String hora_fin) {
		this.hora_fin = hora_fin;
	}

	public Grupo getGrupo() {
		return this.grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dia == null) ? 0 : dia.hashCode());
		result = prime * result + ((grupo == null) ? 0 : grupo.hashCode());
		result = prime * result + ((hora_inicio == null) ? 0 : hora_inicio.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Clase))
			return false;
		Clase other = (Clase) obj;
		if (dia == null) {
			if (other.dia != null)
				return false;
		} else if (!dia.equals(other.dia))
			return false;
		if (grupo == null) {
			if (other.grupo != null)
				return false;
		} else if (!grupo.equals(other.grupo))
			return false;
		if (hora_inicio == null) {
			if (other.hora_inicio != null)
				return false;
		} else if (!hora_inicio.equals(other.hora_inicio))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Clase [dia=" + dia + ", hora_inicio=" + hora_inicio + ", grupo=" + grupo + ", asignatura=" + asignatura
				+ "]";
	}

}
