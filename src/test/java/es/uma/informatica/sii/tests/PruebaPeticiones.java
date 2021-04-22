package es.uma.informatica.sii.tests;

import java.io.IOException;
import java.text.ParseException;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import es.uma.informatica.sii.anotaciones.Requisitos;
import es.uma.informatica.sii.ejb.GestionAlumnos;
import es.uma.informatica.sii.ejb.GestionPeticiones;
import es.uma.informatica.sii.ejb.exceptions.SecretariaException;

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

	}

	@Requisitos({ "RF4.2" })
	@Test
	public void modificaGrupo2Cuatri() throws SecretariaException, IOException, ParseException {

	}
}
