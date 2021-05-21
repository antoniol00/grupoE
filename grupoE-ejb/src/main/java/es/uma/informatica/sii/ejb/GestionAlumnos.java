package es.uma.informatica.sii.ejb;

import java.util.List;

import javax.ejb.Local;

import es.uma.informatica.sii.ejb.exceptions.SecretariaException;
import es.uma.informatica.sii.ejb.exceptions.SecretariaIOException;
import es.uma.informatica.sii.entities.Alumno;
import es.uma.informatica.sii.entities.Expediente;

@Local
public interface GestionAlumnos {

	/**
	 * Los alumnos deben importarse a la base de datos a partir de un archivo excel
	 * preconfigurado. Se debe poder completar la informacion del alumno en la tabla
	 * ALUMNOS. El acceso y procesamiento de estos datos se realiza mediante el
	 * fichero alumnos.csv proporcionado
	 * 
	 * @param file: ruta del archivo donde importar
	 * 
	 * @throws SecretariaIOException si no encuentra en la bbdd alguna referencia a
	 *                               otra entidad o el archivo no existe
	 * @throws SecretariaException   si falla en la importacion en algun dato
	 * 
	 */
	public void importaAlumnos(String file) throws SecretariaIOException, SecretariaException;

	/**
	 * Los alumnos deben importarse a la base de datos a partir de un archivo excel
	 * preconfigurado. Se debe poder completar la informacion del alumno en la tabla
	 * EXPEDIENTE. El acceso y procesamiento de estos datos se realiza mediante el
	 * fichero alumnos.csv proporcionado
	 * 
	 * @param file: ruta del archivo donde importar
	 * 
	 * @throws SecretariaIOException si no encuentra en la bbdd alguna referencia a
	 *                               otra entidad o el archivo no existe
	 * @throws SecretariaException   si falla en la importacion en algun dato
	 * 
	 */
	public void importaExpedientes(String file) throws SecretariaIOException, SecretariaException;

	// METODOS AUXILIARES
	/**
	 * Devuelve el alumno con id dni
	 * 
	 * @param dni: dni del alumno del que se desea obtener instancia
	 * @throws SecretariaException: si el alumno no existe
	 */
	public Alumno obtenerAlumno(String dni) throws SecretariaException;

	/**
	 * Devuelve todos los alumnos
	 */
	public List<Alumno> obtenerListaAlumnos();

	/**
	 * Devuelve el expediente con id num
	 * 
	 * @param num: numero del expediente a buscar
	 * @throws SecretariaException: si el expediente no existe
	 */
	public Expediente obtenerExpediente(int num) throws SecretariaException;

	/**
	 * Devuelve todos los exepdientes
	 */
	public List<Expediente> obtenerListaExpedientes();
	
	public void creaAlumno(Alumno a);
}
