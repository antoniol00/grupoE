package es.uma.informatica.sii.ejb;

import javax.ejb.Local;

import es.uma.informatica.sii.ejb.exceptions.SecretariaException;

@Local
public interface GestionAlumnos {
	
	/**
	 * Los alumnos deben importarse a la base de datos a partir de un archivo
	 * excel preconfigurado. Se debe poder completar tanto la informacion del
	 * alumno en la tabla ALUMNOS como la de la tabla EXPEDIENTE. El acceso
	 * y procesamiento de estos datos se realiza mediante el fichero alumnos.csv proporcionado
	 */
	public void importaAlumnos() throws SecretariaException;
	
	/**
	 * Modifica el alumno pasado como parametro e identificado por su DNI
	 */
	public void modificaAlumno(String dni_alumno) throws SecretariaException;
}
