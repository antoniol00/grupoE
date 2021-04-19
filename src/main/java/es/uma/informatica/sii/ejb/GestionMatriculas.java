package es.uma.informatica.sii.ejb;

import javax.ejb.Local;

import es.uma.informatica.sii.ejb.exceptions.SecretariaException;

@Local
public interface GestionMatriculas {
	
	/**
	 * Las matriculas deben importarse a la base de datos a partir de un archivo
	 * excel preconfigurado. Se debe poder completar la informacion de las
	 * matriculas en la tabla MATRICULA
	 */
	public void importaMatriculas(String file) throws SecretariaException;
	
	/**
	 * Permite listar las matriculas dado un filtro pasado como parametro
	 */
	public void listaMatriculas(String filter) throws SecretariaException;
}
