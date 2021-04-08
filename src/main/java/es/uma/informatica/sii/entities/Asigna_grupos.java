package es.uma.informatica.sii.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Entity implementation class for Entity: Asigna_grupos
 *
 */
@Entity
public class Asigna_grupos implements Serializable {

	public static class Asigna_gruposPK implements Serializable {
		private static final long serialVersionUID = 1L;

		public Integer getAsignatura_referencia() {
			return asignatura_referencia;
		}

		public void setAsignatura_referencia(Integer asignatura_referencia) {
			this.asignatura_referencia = asignatura_referencia;
		}

		public String getMatricula_curso() {
			return matricula_curso;
		}

		public void setMatricula_curso(String matricula_curso) {
			this.matricula_curso = matricula_curso;
		}

		public Integer getMatricula_expediente_numero() {
			return matricula_expediente_numero;
		}

		public void setMatricula_expediente_numero(Integer matricula_expediente_numero) {
			this.matricula_expediente_numero = matricula_expediente_numero;
		}

		private Integer asignatura_referencia;
		private String matricula_curso;
		private Integer matricula_expediente_numero;
	}

	@Id
	@ManyToOne
	private Asignatura asignatura;
	@Id
	@ManyToOne
	private Matricula matricula;
	@Id
	private Expediente expediente;
	@ManyToOne
	private Grupo grupo_id;

	private static final long serialVersionUID = 1L;

	public Asigna_grupos() {
		super();
	}

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	public Expediente getExpediente() {
		return expediente;
	}

	public void setExpediente(Expediente expediente) {
		this.expediente = expediente;
	}

	public Grupo getGrupo_id() {
		return grupo_id;
	}

	public void setGrupo_id(Grupo grupo_id) {
		this.grupo_id = grupo_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((asignatura == null) ? 0 : asignatura.hashCode());
		result = prime * result + ((expediente == null) ? 0 : expediente.hashCode());
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Asigna_grupos))
			return false;
		Asigna_grupos other = (Asigna_grupos) obj;
		if (asignatura == null) {
			if (other.asignatura != null)
				return false;
		} else if (!asignatura.equals(other.asignatura))
			return false;
		if (expediente == null) {
			if (other.expediente != null)
				return false;
		} else if (!expediente.equals(other.expediente))
			return false;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}

}
