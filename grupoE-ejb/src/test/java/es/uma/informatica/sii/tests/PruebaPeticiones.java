package es.uma.informatica.sii.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Date;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import es.uma.informatica.sii.anotaciones.Requisitos;
import es.uma.informatica.sii.ejb.GestionAlumnos;
import es.uma.informatica.sii.ejb.GestionPeticiones;
import es.uma.informatica.sii.ejb.exceptions.SecretariaException;
import es.uma.informatica.sii.ejb.exceptions.SecretariaIOException;
import es.uma.informatica.sii.entities.Peticion;

public class PruebaPeticiones {

	private static final String ALUMNOS_EJB = "java:global/classes/AlumnosImpl";
	private static final String PETICIONES_EJB = "java:global/classes/PeticionesImpl";

	private GestionPeticiones gestionPeticiones;
	private GestionAlumnos gestionAlumnos;

	@Before
	public void setup() throws NamingException, IOException, SecretariaIOException, SecretariaException {
		gestionAlumnos = (GestionAlumnos) SuiteTest.ctx.lookup(ALUMNOS_EJB);
		gestionPeticiones = (GestionPeticiones) SuiteTest.ctx.lookup(PETICIONES_EJB);
		BaseDatos.inicializaBaseDatos("grupoETest");
		gestionAlumnos.importaAlumnos("./DATOS/alumnos.csv");
	}

	@Requisitos({ "RF4.1" })
	@Test
	public void testGenerarIncidenciaSeAlmacenaCorrectamente() throws SecretariaException {
		Peticion p = new Peticion();
		p.setTipo("Incidencia");
		p.setDescripcion("Describe");
		p.setAlumno(gestionAlumnos.obtenerListaAlumnos().get(0));
		Date d = new Date();
		p.setDate(d);

		gestionPeticiones.creaIncidencia(p);

		assertFalse("Peticion no insertada", gestionPeticiones.listaPeticiones().isEmpty());
		assertEquals("Peticion no insertada",1, gestionPeticiones.listaPeticiones().size());

		Peticion pbd = gestionPeticiones.obtenerPeticion(d, gestionAlumnos.obtenerListaAlumnos().get(0).getDni());

		assertTrue("Peticion no creada con los valores correctos",
				pbd.getDescripcion().equals("Describe") && pbd.getTipo().equals("Incidencia"));

	}

	@Requisitos({ "RF4.1" })
	@Test(expected = SecretariaException.class)
	public void testGenerarIncidenciaYaExistente() throws SecretariaException {
		Peticion p = new Peticion();
		p.setTipo("Incidencia");
		p.setDescripcion("Describe");
		p.setAlumno(gestionAlumnos.obtenerListaAlumnos().get(0));
		Date d = new Date();
		p.setDate(d);

		gestionPeticiones.creaIncidencia(p);
		gestionPeticiones.creaIncidencia(p);
	}

	@Requisitos({ "RF4.1" })
	@Test
	public void testEditarIncidenciaSeAlmacenaCorrectamente() throws SecretariaException {
		Peticion p = new Peticion();
		p.setTipo("Incidencia");
		p.setDescripcion("Describe");
		p.setAlumno(gestionAlumnos.obtenerListaAlumnos().get(0));
		Date d = new Date();
		p.setDate(d);

		gestionPeticiones.creaIncidencia(p);

		Peticion pbd = gestionPeticiones.obtenerPeticion(d, gestionAlumnos.obtenerListaAlumnos().get(0).getDni());
		pbd.setDescripcion("NUEVO");

		gestionPeticiones.editaIncidencia(d, gestionAlumnos.obtenerListaAlumnos().get(0).getDni(), pbd);

		assertEquals("Peticion no editada",
				"NUEVO",
				gestionPeticiones.obtenerPeticion(d, gestionAlumnos.obtenerListaAlumnos().get(0).getDni())
						.getDescripcion());
	}

	@Requisitos({ "RF4.1" })
	@Test(expected = SecretariaException.class)
	public void testEditarIncidenciaNoExistente() throws SecretariaException {
		Peticion p = new Peticion();
		p.setTipo("Incidencia");
		p.setDescripcion("Describe");
		p.setAlumno(gestionAlumnos.obtenerListaAlumnos().get(0));
		Date d = new Date();
		p.setDate(d);
		gestionPeticiones.editaIncidencia(d, gestionAlumnos.obtenerListaAlumnos().get(0).getDni(), p);

	}

	@Requisitos({ "RF4.1" })
	@Test
	public void testEliminarIncidenciaCorrectamente() throws SecretariaException {
		Peticion p = new Peticion();
		p.setTipo("Incidencia");
		p.setDescripcion("Describe");
		p.setAlumno(gestionAlumnos.obtenerListaAlumnos().get(0));
		Date d = new Date();
		p.setDate(d);

		gestionPeticiones.creaIncidencia(p);

		gestionPeticiones.borraIncidencia(d, gestionAlumnos.obtenerListaAlumnos().get(0).getDni());

		assertTrue("Lista no vacia", gestionPeticiones.listaPeticiones().isEmpty());
		assertFalse("Peticion no borrada correctamente", gestionPeticiones.listaPeticiones().contains(p));
	}

	@Requisitos({ "RF4.1" })
	@Test(expected = SecretariaException.class)
	public void testBorrarIncidenciaNoExistente() throws SecretariaException {
		gestionPeticiones.borraIncidencia(new Date(), "X");

	}

}
