package es.uma.informatica.sii.ejb;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.ejb.Local;

import es.uma.informatica.sii.ejb.exceptions.SecretariaException;
import es.uma.informatica.sii.entities.Matricula;

@Local
public interface GestionMatriculas {
	
	/**
	 * Las matriculas deben importarse a la base de datos a partir de un archivo
	 * excel preconfigurado. Se debe poder completar la informacion de las
	 * matriculas en la tabla MATRICULA
	 */
	public void importaMatriculas(String file) throws SecretariaException, IOException,ParseException;
	
	/**
	 * Permite listar las matriculas dado un filtro pasado como parametro
	 * 
	 * @param: String filter: puede contener las cadenas "Date" para ordenar por orden de recepcion de la matricula, "ASC" para ordenar ascendente
	 * por la ID, "DES" para ordenar descendente por la ID, "EXP" ordenado por el numero de referencia del expediente
	 */
	public List<Matricula> listaMatriculas(String filter) throws SecretariaException;
	
	//NO NECESITA TEST
	/**
	 * Devuelve la matricula especificada
	 * @param curso
	 * @param expediente
	 * @throws SecretariaException
	 */
	public Matricula obtenerMatricula(String curso, Integer expediente) throws SecretariaException;
}
