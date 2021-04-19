package es.uma.informatica.sii.ejb;

import javax.ejb.Local;

import es.uma.informatica.sii.ejb.exceptions.SecretariaException;
import es.uma.informatica.sii.entities.Asignatura;
import es.uma.informatica.sii.entities.Grupos_asig;

@Local
public interface GestionAsignaturas {

	/**
	 * Importa de un archivo excel la informacion necesaria
	 * para rellenar la tabla asignaturas
	 * 
	 * @param file: ruta del archivo donde importarS
	 */
	public void importaAsignaturas(String file) throws SecretariaException;
	
	/**
	 * Modifica los datos de la asignatura pasada como parametro
	 * 
	 * @param codigo,titulacion: id de la asignatura a cambiar
	 * @param asig: información a actualizar
	 */
	public void modificarAsignatura(String codigo, String titulacion, Asignatura asig) throws SecretariaException;
	
	/**
	 * Borra una asignatura. Realmente no se borra de la base de datos, simplemente se
	 * marca como no ofertada (campo ofertada a 'N')
	 */
	public void borrarAsignatura(String codigo, String titulacion) throws SecretariaException;
	
	/**
	 * Permite definir grupos rellenando la tabla Grupos_asig para la asignatura
	 * 
	 * @param ga: grupo con asignatura para insertar en la bbdd
	 */
	public void definirGrupos(Grupos_asig ga) throws SecretariaException;
}
