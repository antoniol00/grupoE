package es.uma.informatica.sii.entities;

import java.io.Serializable;
import java.lang.Double;
import java.lang.String;

/**
 * ID class for entity: Matricula
 *
 */ 
public class MatriculaPK  implements Serializable {   
   
	         
	private String curso;         
	private Integer expediente;
	private static final long serialVersionUID = 1L;

	public MatriculaPK() {}

	

	public String getCurso() {
		return this.curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}
	

	public Expediente getExpedienteNum() {
		return this.expediente_número;
	}

	public void setExpedienteNum(Expediente expediente_número) {
		this.expediente_número = expediente_número;
	}
	
   
	/*
	 * @see java.lang.Object#equals(Object)
	 */	
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof MatriculaPK)) {
			return false;
		}
		MatriculaPK other = (MatriculaPK) o;
		return true
			&& (getCurso() == null ? other.getCurso() == null : getCurso().equals(other.getCurso()))
			&& (getExpedienteNum() == null ? other.getExpedienteNum() == null : getExpedienteNum().equals(other.getExpedienteNum()));
	}
	
	/*	 
	 * @see java.lang.Object#hashCode()
	 */	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (getCurso() == null ? 0 : getCurso().hashCode());
		result = prime * result + (getExpedienteNum() == null ? 0 : getExpedienteNum().hashCode());
		return result;
	}
   
   
}
