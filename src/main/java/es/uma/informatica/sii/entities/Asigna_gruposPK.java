package es.uma.informatica.sii.entities;

import java.io.Serializable;

public class Asigna_gruposPK implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer asignatura_referencia;
	private String matricula_curso;
	private Integer matricula_expediente_numero;
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((asignatura_referencia == null) ? 0 : asignatura_referencia.hashCode());
		result = prime * result + ((matricula_curso == null) ? 0 : matricula_curso.hashCode());
		result = prime * result + ((matricula_expediente_numero == null) ? 0 : matricula_expediente_numero.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Asigna_gruposPK))
			return false;
		Asigna_gruposPK other = (Asigna_gruposPK) obj;
		if (asignatura_referencia == null) {
			if (other.asignatura_referencia != null)
				return false;
		} else if (!asignatura_referencia.equals(other.asignatura_referencia))
			return false;
		if (matricula_curso == null) {
			if (other.matricula_curso != null)
				return false;
		} else if (!matricula_curso.equals(other.matricula_curso))
			return false;
		if (matricula_expediente_numero == null) {
			if (other.matricula_expediente_numero != null)
				return false;
		} else if (!matricula_expediente_numero.equals(other.matricula_expediente_numero))
			return false;
		return true;
	}
}
