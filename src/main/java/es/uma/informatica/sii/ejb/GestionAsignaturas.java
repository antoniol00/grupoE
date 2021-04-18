package es.uma.informatica.sii.ejb;

import javax.ejb.Local;

import es.uma.informatica.sii.ejb.exceptions.SecretariaException;

@Local
public interface GestionAsignaturas {

	/**
	 * Importa de un archivo excel la informacion necesaria
	 * para rellenar la tabla asignaturas
	 */
	public void importaAsignaturas() throws SecretariaException;
	
	/**
	 * Modifica los datos de la asignatura pasada como parametro
	 */
	public void modificarAsignatura(String codigo, String titulacion) throws SecretariaException;
	
	/**
	 * Borra una asignatura. Realmente no se borra de la base de datos, simplemente se
	 * marca como no ofertada (campo ofertada a 'N')
	 */
	public void borrarAsignatura(String codigo, String titulacion) throws SecretariaException;
	
	/**
	 * Permite definir grupos rellenando la tabla Grupos_asig para la asignatura
	 */
	public void definirGrupos(String codigo, String titulacion) throws SecretariaException;
}
