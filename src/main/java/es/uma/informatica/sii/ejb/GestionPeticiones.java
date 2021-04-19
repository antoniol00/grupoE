package es.uma.informatica.sii.ejb;

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
}
