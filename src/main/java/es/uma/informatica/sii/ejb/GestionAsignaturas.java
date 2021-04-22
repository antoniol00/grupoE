package es.uma.informatica.sii.ejb;

import java.util.List;

import javax.ejb.Local;

import es.uma.informatica.sii.ejb.exceptions.SecretariaException;
import es.uma.informatica.sii.ejb.exceptions.SecretariaIOException;
import es.uma.informatica.sii.entities.Asignatura;

@Local
public interface GestionAsignaturas {

	/**
	 * Importa de un archivo excel la informacion necesaria para rellenar la tabla
	 * ASIGNATURA
	 * 
	 * @param file: ruta del archivo donde importar
	 * @throws SecretariaIOException si el archivo no existe
	 * @throws SecretariaException   para cualquier fallo en el archivo
	 */
	public void importaAsignaturas(String file) throws SecretariaIOException, SecretariaException;

	/**
	 * Importa de un archivo excel la informacion necesaria para rellenar la tabla
	 * OPTATIVA
	 * 
	 * @param file: ruta del archivo donde importar
	 * @throws SecretariaIOException: si el archivo no existe
	 * @throws SecretariaException    para cualquier fallo en el archivo
	 */
	public void importaOptativas(String file) throws SecretariaIOException, SecretariaException;

	/**
	 * Modifica los datos de la asignatura pasada como parametro
	 * 
	 * @param codigo,titulacion: id de la asignatura a cambiar
	 * @param asig:              información a actualizar
	 */
	public void modificarAsignatura(int codigo, int titulacion, Asignatura asig) throws SecretariaException;

	/**
	 * Borra una asignatura
	 */
	public void borrarAsignatura(int codigo, int titulacion) throws SecretariaException;
	
	/**
	 * Marca una asignatura como no ofertada
	 * 
	 * @throws SecretariaException: si no existe la asignatura o ya esta desactivada
	 */
	public void desactivarAsignatura(int codigo, int titulacion) throws SecretariaException;

	/**
	 * Permite definir grupos rellenando la tabla Grupos_asig para la asignatura y
	 * grupo dado
	 * 
	 * @param codigo, titulacion: id de la asignatura
	 * @param id:     id del grupo
	 * @param curso   : curso de la asignatura
	 * @throws SecretariaException: si no existe la asignatura o grupo que se desea
	 *                              asignar
	 */
	public void definirGrupos(int codigo, int titulacion, String id, String curso) throws SecretariaException;

	//METODOS AUXILIARES
	/**
	 * Devuelve la asignatura dada su clave compuesta
	 * 
	 * @param codigo, titulacion: id asignatura
	 * @throws SecretariaException: si no existe
	 */
	public Asignatura obtenerAsignatura(int codigo, int titulacion) throws SecretariaException;

	/**
	 * Devuelve todas las asignaturas
	 */
	public List<Asignatura> listarAsignaturas();

}
