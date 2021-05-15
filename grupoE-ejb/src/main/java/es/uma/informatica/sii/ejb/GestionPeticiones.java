package es.uma.informatica.sii.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import es.uma.informatica.sii.ejb.exceptions.SecretariaException;
import es.uma.informatica.sii.entities.Peticion;

@Local
public interface GestionPeticiones {

	/**
	 * Permite crear e introducir en el sistema una incidencia
	 * 
	 * @param p: objeto peticion
	 * @throws SecretariaException: si el objeto ya existe
	 */
	public void creaIncidencia(Peticion p) throws SecretariaException;

	/**
	 * Permite editar incidencia
	 * 
	 * @param p:        objeto peticion con nueva informacion (obtenido antes con
	 *                  find)
	 * @param date,dni: pk peticion
	 * @throws SecretariaException: si la peticion no existe
	 */
	public void editaIncidencia(Date d, String dni, Peticion p) throws SecretariaException;

	/**
	 * Permite borrar incidencia
	 * 
	 * @param date,dni: pk peticion
	 * @throws SecretariaException: si la peticion no existe
	 */
	public void borraIncidencia(Date d, String dni) throws SecretariaException;

	// METODOS AUXILIARES

	/**
	 * Obtiene la peticion especificada
	 * 
	 * @param date,dni: pk peticion
	 * @throws SecretariaException: si no existe la peticion
	 */
	public Peticion obtenerPeticion(Date d, String dni) throws SecretariaException;

	/**
	 * Lista todas las peticiones de la base de datos
	 */
	public List<Peticion> listaPeticiones();
}
