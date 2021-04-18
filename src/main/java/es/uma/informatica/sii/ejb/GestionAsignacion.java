package es.uma.informatica.sii.ejb;

import javax.ejb.Local;

import es.uma.informatica.sii.ejb.exceptions.SecretariaException;

@Local
public interface GestionAsignacion {

	/**
	 * Realiza la asignacion de grupos a alumnos rellenando la tabla Asigna_grupos
	 */
	public void asignaGruposAlumnos() throws SecretariaException;
}
