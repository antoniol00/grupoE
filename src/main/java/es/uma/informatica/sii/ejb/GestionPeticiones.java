package es.uma.informatica.sii.ejb;

import javax.ejb.Local;

import es.uma.informatica.sii.ejb.exceptions.SecretariaException;

@Local
public interface GestionPeticiones {
	/**
	 * Permite crear e introducir en el sistema una incidencia
	 */
	public void creaIncidencia(String i) throws SecretariaException;
	
}
