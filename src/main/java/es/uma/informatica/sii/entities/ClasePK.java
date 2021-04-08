package es.uma.informatica.sii.entities;

import java.io.Serializable;

/**
 * ID class for entity: Clase
 *
 */
public class ClasePK implements Serializable {

	private String dia;
	private String hora_inicio;
	private String grupo_id;
	private static final long serialVersionUID = 1L;

	public ClasePK() {
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

	public String getGrupo_id() {
		return this.grupo_id;
	}

	public void setGrupo_id(String grupo_id) {
		this.grupo_id = grupo_id;
	}

	/*
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof ClasePK)) {
			return false;
		}
		ClasePK other = (ClasePK) o;
		return true && (getDia() == null ? other.getDia() == null : getDia().equals(other.getDia()))
				&& (getHora_inicio() == null ? other.getHora_inicio() == null
						: getHora_inicio().equals(other.getHora_inicio()))
				&& (getGrupo_id() == null ? other.getGrupo_id() == null : getGrupo_id().equals(other.getGrupo_id()));
	}

	/*
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (getDia() == null ? 0 : getDia().hashCode());
		result = prime * result + (getHora_inicio() == null ? 0 : getHora_inicio().hashCode());
		result = prime * result + (getGrupo_id() == null ? 0 : getGrupo_id().hashCode());
		return result;
	}

}
