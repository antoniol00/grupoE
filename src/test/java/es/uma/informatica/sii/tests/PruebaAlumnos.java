package es.uma.informatica.sii.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import es.uma.informatica.sii.anotaciones.Requisitos;
import es.uma.informatica.sii.ejb.GestionAlumnos;
import es.uma.informatica.sii.ejb.exceptions.SecretariaException;
import es.uma.informatica.sii.ejb.exceptions.SecretariaIOException;
import es.uma.informatica.sii.entities.Alumno;

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
	public void testImportarAlumnosListaNoVacia() throws SecretariaException, SecretariaIOException {
		gestionAlumnos.importaAlumnos("./DATOS/alumnos.csv");
		assertTrue("Error al importar alumnos", gestionAlumnos.obtenerListaAlumnos().size() != 0);
	}

	// Importar alumnos debe devolver 1508 entradas
	@Requisitos({ "RF2.1" })
	@Test
	public void testImportarAlumnosTamanoCorrecto() throws SecretariaException, SecretariaIOException {
		gestionAlumnos.importaAlumnos("./DATOS/alumnos.csv");
		assertEquals("Error al importar alumnos", gestionAlumnos.obtenerListaAlumnos().size(), 1508);
	}

	// Al modificar un alumno se modifican los cambios especificados en la bbdd
	@Requisitos({ "RF2.1", "RF2.2" })
	@Test
	public void testModificarAlumnos() throws SecretariaException, SecretariaIOException {
		gestionAlumnos.importaAlumnos("./DATOS/alumnos.csv");
		Alumno a = gestionAlumnos.obtenerListaAlumnos().get(0);
		a.setCodigo_postal(14900);
		a.setNombre_completo("Prueba Tests");
		gestionAlumnos.modificaAlumno(a.getDni(), a);
		assertEquals("Error al modificar el alumno", Integer.valueOf(14900),
				gestionAlumnos.obtenerAlumno(a.getDni()).getCodigo_postal());
		assertEquals("Error al modificar el alumno", "Prueba Tests",
				gestionAlumnos.obtenerAlumno(a.getDni()).getNombre_completo());
	}

	// Al modificar un alumno que no existe, lanza excepcion
	@Requisitos({ "RF2.1", "RF2.2" })
	@Test(expected = SecretariaException.class)
	public void testModificarAlumnoInexsistente() throws SecretariaException, SecretariaIOException {
		gestionAlumnos.importaAlumnos("./DATOS/alumnos.csv");
		gestionAlumnos.modificaAlumno("No existo", gestionAlumnos.obtenerListaAlumnos().get(0));

	}
}
