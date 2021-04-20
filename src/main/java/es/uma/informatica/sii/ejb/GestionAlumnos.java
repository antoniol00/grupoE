package es.uma.informatica.sii.ejb;

import java.io.IOException;
import java.util.List;

import javax.ejb.Local;

import es.uma.informatica.sii.ejb.exceptions.SecretariaException;
import es.uma.informatica.sii.entities.Alumno;

@Local
public interface GestionAlumnos {

	/**
	 * Los alumnos deben importarse a la base de datos a partir de un archivo excel
	 * preconfigurado. Se debe poder completar tanto la informacion del alumno en la
	 * tabla ALUMNOS como la de la tabla EXPEDIENTE. El acceso y procesamiento de
	 * estos datos se realiza mediante el fichero alumnos.csv proporcionado
	 * 
	 * @param file: ruta del archivo donde importar
	 * 
	 * @throws IOException         si hay un error en el acceso a alumnos.csv
	 * @throws SecretariaException si no encuentra en la bbdd alguna referencia a
	 *                             otra entidad
	 */
	public void importaAlumnos(String file) throws SecretariaException, IOException;

	/**
	 * Modifica el alumno pasado como parametro e identificado por su DNI
	 * 
	 * @param dni_alumno: alumno que se desea modificar
	 * @param al:         informacion actualizada del alumno
	 * 
	 * @throws SecretariaException si no encuentra en la bbdd alguna referencia al
	 *                             alumno a modificar
	 */
	public void modificaAlumno(String dni_alumno, Alumno al) throws SecretariaException;

	// NO NECESITAN TEST
	/**
	 * Devuelve el alumno con id dni
	 * 
	 * @param dni
	 * @throws SecretariaException
	 */
	public Alumno obtenerAlumno(String dni) throws SecretariaException;

	/**
	 * Devuelve todos los alumnos
	 */
	public List<Alumno> obtenerListaAlumnos() throws SecretariaException;
}
