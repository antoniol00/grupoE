package es.uma.informatica.sii.tests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.text.ParseException;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import es.uma.informatica.sii.anotaciones.Requisitos;
import es.uma.informatica.sii.ejb.GestionAlumnos;
import es.uma.informatica.sii.ejb.GestionPeticiones;
import es.uma.informatica.sii.ejb.exceptions.SecretariaException;
import es.uma.informatica.sii.entities.Alumno;
import es.uma.informatica.sii.entities.Peticion;

public class PruebaPeticiones {

	private static final String ALUMNOS_EJB = "java:global/classes/AlumnosImpl";
	private static final String PETICIONES_EJB = "java:global/classes/PeticionesImpl";

	private GestionPeticiones gestionPeticiones;
	private GestionAlumnos gestionAlumnos;

	@Before
	public void setup() throws NamingException, IOException {
		gestionAlumnos = (GestionAlumnos) SuiteTest.ctx.lookup(ALUMNOS_EJB);
		gestionPeticiones = (GestionPeticiones) SuiteTest.ctx.lookup(PETICIONES_EJB);
		BaseDatos.inicializaBaseDatos("grupoETest");
	}

	@Requisitos({ "RF4.1" })
	@Test
	public void generarIncidencia() throws SecretariaException, IOException, ParseException {
		Peticion p = new Peticion();
		gestionPeticiones.creaIncidencia(p);
		Integer id = p.getId();
		assertTrue("Error al generar el incidente", gestionPeticiones.obtenerPeticion(id) == p);
		
	}

	@Requisitos({ "RF4.2" })
	@Test
	public void modificaGrupo2Cuatri() throws SecretariaException, IOException, ParseException {
		
	}
}
