package es.uma.informatica.sii.entities;

import es.uma.informatica.sii.entities.Asignatura;
import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Optativa
 *
 */
@Entity

public class Optativa extends Asignatura implements Serializable {

	private Integer plazas;
	private String mencion;
	private static final long serialVersionUID = 1L;

	public Optativa() {
		super();
	}   
	public Integer getPlazas() {
		return this.plazas;
	}

	public void setPlazas(Integer plazas) {
		this.plazas = plazas;
	}   
	public String getMencion() {
		return this.mencion;
	}

	public void setMencion(String mencion) {
		this.mencion = mencion;
	}
   
}
