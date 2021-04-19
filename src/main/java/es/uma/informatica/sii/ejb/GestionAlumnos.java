package es.uma.informatica.sii.ejb;

import javax.ejb.Local;

import es.uma.informatica.sii.ejb.exceptions.SecretariaException;
import es.uma.informatica.sii.entities.Alumno;

@Local
public interface GestionAlumnos {
	
	/**
	 * Los alumnos deben importarse a la base de datos a partir de un archivo
	 * excel preconfigurado. Se debe poder completar tanto la informacion del
	 * alumno en la tabla ALUMNOS como la de la tabla EXPEDIENTE. El acceso
	 * y procesamiento de estos datos se realiza mediante el fichero alumnos.csv proporcionado
	 * 
	 * @param file: ruta del archivo donde importarS
	 */
	public void importaAlumnos(String file) throws SecretariaException;
	
	/**
	 * Modifica el alumno pasado como parametro e identificado por su DNI
	 * 
	 * @param dni_alumno: alumno que se desea modificar
	 * @param al: informacion actualizada del alumno
	 */
	public void modificaAlumno(String dni_alumno, Alumno al) throws SecretariaException;
}
