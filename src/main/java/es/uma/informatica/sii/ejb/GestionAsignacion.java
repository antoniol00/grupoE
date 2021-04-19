package es.uma.informatica.sii.ejb;

import javax.ejb.Local;

import es.uma.informatica.sii.ejb.exceptions.SecretariaException;
import es.uma.informatica.sii.entities.Clase;
import es.uma.informatica.sii.entities.Grupo;

@Local
public interface GestionAsignacion {

	/**
	 * Realiza la asignacion de grupos a alumnos rellenando la tabla Asigna_grupos
	 */
	public void asignaGruposAlumnos() throws SecretariaException;
	
	/**
	 * Crea un grupo
	 */
	public void creaGrupo(Grupo g) throws SecretariaException;
	
	/**
	 * Borra un grupo
	 */
	public void borraGrupo(String id) throws SecretariaException;
	
	/**
	 * Crea una clase
	 */
	public void creaClase(Clase c) throws SecretariaException;
	
	/**
	 * Borra una clase
	 */
	public void borraClase(String dia, String hora_inicio, String id_grupo) throws SecretariaException;
}
