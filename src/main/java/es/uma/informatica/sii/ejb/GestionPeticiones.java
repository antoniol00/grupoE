package es.uma.informatica.sii.ejb;

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
	 * @param p:  objeto peticion con nueva informacion (obtenido antes con find)
	 * @param id: id de la peticion a modificar
	 * @throws SecretariaException: si la peticion no existe
	 */
	public void editaIncidencia(Integer id, Peticion p) throws SecretariaException;

	/**
	 * Permite borrar incidencia
	 * 
	 * @param id: id de la peticion a borrar
	 * @throws SecretariaException: si la peticion no existe
	 */
	public void borraIncidencia(Integer id) throws SecretariaException;

	//METODOS AUXILIARES

	/**
	 * Obtiene la peticion especificada
	 * 
	 * @param id: id de la peticion a consultar
	 * @throws SecretariaException: si no existe la peticion
	 */
	public Peticion obtenerPeticion(Integer id) throws SecretariaException;

	/**
	 * Lista todas las peticiones de la base de datos
	 */
	public List<Peticion> listaPeticiones();
}
