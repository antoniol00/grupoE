package es.uma.informatica.sii.ejb;

import java.util.List;

import javax.ejb.Local;

import es.uma.informatica.sii.ejb.exceptions.SecretariaException;
import es.uma.informatica.sii.entities.Peticion;

@Local
public interface GestionPeticiones {
	/**
	 * Permite crear e introducir en el sistema una incidencia
	 */
	public void creaIncidencia(Peticion p) throws SecretariaException;
	
	/**
	 * Permite editar incidencia
	 */
	public void editaIncidencia(Integer id, Peticion p) throws SecretariaException;
	
	/**
	 * Permite borrar incidencia
	 */
	public void borraIncidencia(Integer id) throws SecretariaException;
	
	//NO NECESITA TEST
	
	/**
	 * Obtiene la peticion especificada
	 * @param id
	 * @return
	 * @throws SecretariaException
	 */
	public Peticion obtenerPeticion(Integer id) throws SecretariaException;
	
	/**
	 * Lista todas las peticiones de la base de datos
	 * @return
	 * @throws SecretariaException
	 */
	public List<Peticion> listaPeticiones() throws SecretariaException;
}
