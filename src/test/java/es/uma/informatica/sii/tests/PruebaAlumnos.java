package es.uma.informatica.sii.tests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import es.uma.informatica.sii.anotaciones.Requisitos;
import es.uma.informatica.sii.ejb.GestionAlumnos;
import es.uma.informatica.sii.ejb.exceptions.SecretariaException;

public class PruebaAlumnos {

	private static final String ALUMNOS_EJB = "java:global/classes/AlumnosImpl";

	private GestionAlumnos gestionAlumnos;

	@Before
	public void setup() throws NamingException, IOException {
		gestionAlumnos = (GestionAlumnos) SuiteTest.ctx.lookup(ALUMNOS_EJB);
		BaseDatos.inicializaBaseDatos("grupoETest");
	}

	// Importar alumnos debe devolver una lista no vacia
	@Requisitos({ "RF2.1" })
	@Test
	public void testImportarAlumnos() throws SecretariaException {
		gestionAlumnos.importaAlumnos("./DATOS/alumnos.csv");
		assertTrue("Error al importar alumnos", gestionAlumnos.obtenerListaAlumnos().size() != 0);
	}

	// Importar alumnos de un fichero inexistente debe lanzar IOException
	@Requisitos({ "RF2.1" })
	@Test
	public void testImportarAlumnoFicheroInexistente() throws SecretariaException {
		
	}

	@Requisitos({ "RF2.2" })
	@Test
	public void testModificarAlumnos() throws SecretariaException {

	}

	@Requisitos({ "RF2.2" })
	@Test
	public void testModificarAlumnoInexsistente() throws SecretariaException {

	}
}
