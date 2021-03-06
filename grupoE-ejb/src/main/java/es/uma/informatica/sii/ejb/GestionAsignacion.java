package es.uma.informatica.sii.ejb;

import java.util.List;

import javax.ejb.Local;

import es.uma.informatica.sii.ejb.exceptions.SecretariaException;
import es.uma.informatica.sii.entities.Asigna_grupos;
import es.uma.informatica.sii.entities.Clase;
import es.uma.informatica.sii.entities.Grupo;

@Local
public interface GestionAsignacion {

	/**
	 * Realiza la asignacion de grupos a alumnos rellenando la tabla Asigna_grupos
	 * 
	 * @throws SecretariaException: si se encuentra algun error fatal en la
	 *                              asignacion
	 */
	public void asignaGruposAlumnos() throws SecretariaException;

	/**
	 * Modifica la asignacion de una matricula a una asignatura, cambiando el grupo
	 * asignado para esta
	 * 
	 * @param codigo,   titulacion: pk de asignatura
	 * @param curso,    expediente: pk de matricula
	 * @param id_grupo: id de grupo que se desea asignar manualmente
	 * 
	 * @throws SecretariaException: si la asignatura, la matricula o el grupo no
	 *                              existen en la bbdd
	 */
	public void modificaGrupo(int codigo, int titulacion, String curso, int expediente, String id_grupo)
			throws SecretariaException;

	/**
	 * Crea un grupo
	 * 
	 * @param g: grupo que se desea crear
	 * @throws SecretariaException si el grupo ya existia
	 */
	public void creaGrupo(Grupo g) throws SecretariaException;

	/**
	 * Borra un grupo
	 * 
	 * @param g: grupo que se desea borrar
	 * @throws SecretariaException si el grupo no existe
	 */
	public void borraGrupo(String id) throws SecretariaException;

	/**
	 * Crea una clase
	 * 
	 * @param c: clase que se desea crear
	 * @throws SecretariaException si el clase ya existia
	 */
	public void creaClase(Clase c) throws SecretariaException;

	/**
	 * Borra una clase
	 * 
	 * @param dia, hora_inicio, id_grupo: identificadores del grupo
	 * @throws SecretariaException si la clase no existe
	 */
	public void borraClase(String dia, String hora_inicio, String id_grupo) throws SecretariaException;

	// METODOS AUXILIARES
	/**
	 * Devuelve lista provisional con asignacion: MATRICULA-ASIGNATURA-GRUPO
	 */
	public List<Asigna_grupos> listaAsignacionProvisional();

	/**
	 * Devuelve la asignacion pasada como parametro
	 */
	public Asigna_grupos obtieneAsignacion(int codigo, int titulacion, String curso, int expediente)
			throws SecretariaException;

	/**
	 * Devuelve el grupo pasado como parametro
	 * 
	 * @param id: id grupo
	 * @throws SecretariaException si el grupo no existe
	 */
	public Grupo obtenerGrupo(String id) throws SecretariaException;

	/**
	 * Devuelve la clase pasada como parametro
	 * 
	 * @throws SecretariaException si la clase no existe
	 */
	public Clase obtenerClase(String dia, String hora_inicio, String id_grupo) throws SecretariaException;

	/**
	 * lista todos los grupos creados en la bbdd
	 */
	public List<Grupo> listaGrupos();

	/**
	 * lista todas las clases creadas en la bbdd
	 */
	public List<Clase> listaClases();

	/**
	 * Devuelve true si el alumno tiene colisiones de horario en sus asignaturas y
	 * false en caso contrario.
	 * 
	 * @param matricula: matricula del alumno
	 */
	public boolean ColisionesHorario(int matricula) throws SecretariaException;

	public void borraAsignacion(Asigna_grupos ag);

}
