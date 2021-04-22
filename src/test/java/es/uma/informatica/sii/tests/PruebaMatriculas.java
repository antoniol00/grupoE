package es.uma.informatica.sii.tests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.text.ParseException;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import es.uma.informatica.sii.anotaciones.Requisitos;
import es.uma.informatica.sii.ejb.GestionAlumnos;
import es.uma.informatica.sii.ejb.GestionMatriculas;
import es.uma.informatica.sii.ejb.exceptions.SecretariaException;
import es.uma.informatica.sii.ejb.exceptions.SecretariaIOException;

public class PruebaMatriculas {

	private static final String ALUMNOS_EJB = "java:global/classes/AlumnosImpl";
	private static final String MATRICULAS_EJB = "java:global/classes/MatriculasImpl";

	private GestionAlumnos gestionAlumnos;
	private GestionMatriculas gestionMatriculas;

	@Before
	public void setup() throws NamingException, IOException {
		gestionAlumnos = (GestionAlumnos) SuiteTest.ctx.lookup(ALUMNOS_EJB);
		gestionMatriculas = (GestionMatriculas) SuiteTest.ctx.lookup(MATRICULAS_EJB);
		BaseDatos.inicializaBaseDatos("grupoETest");
	}

	@Requisitos({ "RF3.1" })
	@Test
	public void importarMatriculas() throws SecretariaIOException, SecretariaException {
		gestionAlumnos.importaAlumnos("./DATOS/alumnos.csv");
		gestionMatriculas.importaMatriculas("./DATOS/alumnos.csv");
		String filter = "ASC";
		assertTrue("Error al importar matriculas", gestionMatriculas.listaMatriculas(filter).size() != 0);
	}

	@Requisitos({ "RF3.2" })
	@Test
	public void listarMatriculas() throws SecretariaException, IOException, ParseException {

	}

}
