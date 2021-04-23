package es.uma.informatica.sii.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.text.ParseException;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import es.uma.informatica.sii.anotaciones.Requisitos;
import es.uma.informatica.sii.ejb.GestionAsignaturas;
import es.uma.informatica.sii.ejb.exceptions.SecretariaException;
import es.uma.informatica.sii.ejb.exceptions.SecretariaIOException;
import es.uma.informatica.sii.entities.Asignatura;

public class PruebaAsignaturas {

	private static final String ASIGNATURAS_EJB = "java:global/classes/AsignaturasImpl";

	private GestionAsignaturas gestionAsignaturas;

	@Before
	public void setup() throws NamingException, IOException {
		gestionAsignaturas = (GestionAsignaturas) SuiteTest.ctx.lookup(ASIGNATURAS_EJB);
		BaseDatos.inicializaBaseDatos("grupoETest");
	}

	// Importar asignaturas debe devolver una lista no vacia
	@Requisitos({ "RF1.1" })
	@Test
	public void testImportarAsignaturasListaNoVacia() throws SecretariaIOException, SecretariaException {
		gestionAsignaturas.importaAsignaturas("./DATOS/asignaturas.xlsx");
		assertTrue("Error al importar asignaturas", gestionAsignaturas.listarAsignaturas().size() != 0);
	}

	// Importar asignaturas debe devolver una lista con 364 entradas
	@Requisitos({ "RF1.1" })
	@Test
	public void testImportarAsignaturasTamanoCorrecto() throws SecretariaIOException, SecretariaException {
		gestionAsignaturas.importaAsignaturas("./DATOS/asignaturas.xlsx");
		assertEquals("Error al importar asignaturas", gestionAsignaturas.listarAsignaturas().size(), 364);
	}

	// Al modificar una asignatura se modifican los cambios especificados en la bbdd
	@Requisitos({ "RF1.1", "RF1.2" })
	@Test
	public void testModificarAsignaturas() throws SecretariaException, SecretariaIOException {
		gestionAsignaturas.importaAsignaturas("./DATOS/asignaturas.xlsx");
		Asignatura asig = gestionAsignaturas.listarAsignaturas().get(0);
		asig.setCurso(8);
		int codigo = asig.getCodigo();
		int titu = asig.getTitulacion().getCodigo();
		gestionAsignaturas.modificarAsignatura(codigo, titu, asig);
		assertEquals("Error al modificar la asignatura", asig.getCurso(),
				gestionAsignaturas.obtenerAsignatura(codigo, titu).getCurso());
	}

	// Al modificar una asignatura que no existe, lanza excepcion
	@Requisitos({ "RF1.1", "RF1.2" })
	@Test(expected = SecretariaException.class)
	public void testModificarAlumnoInexsistente() throws SecretariaException, SecretariaIOException {
		gestionAsignaturas.importaAsignaturas("./DATOS/asignaturas.xlsx");
		gestionAsignaturas.modificarAsignatura(0, 0, gestionAsignaturas.listarAsignaturas().get(0));

	}

	// Borrar todas las asignaturas queda con una lista vacia
	@Requisitos({ "RF1.1", "RF1.3" })
	@Test
	public void borrarAsignaturas() throws SecretariaException, SecretariaIOException {
		gestionAsignaturas.importaAsignaturas("./DATOS/asignaturas.xlsx");
		for (Asignatura as : gestionAsignaturas.listarAsignaturas())
			gestionAsignaturas.borrarAsignatura(as.getCodigo(), as.getTitulacion().getCodigo());
		assertTrue("Error al borrar la asignatura", gestionAsignaturas.listarAsignaturas().size() == 0);
	}

	// Al borrar un alumno que no existe, lanza excepcion
	@Requisitos({ "RF1.1", "RF1.3" })
	@Test(expected = SecretariaException.class)
	public void testBorrarAsignaturaInexsistente() throws SecretariaException, SecretariaIOException {
		gestionAsignaturas.importaAsignaturas("./DATOS/asignaturas.xlsx");
		gestionAsignaturas.modificarAsignatura(0, 0, gestionAsignaturas.listarAsignaturas().get(0));

	}

	// Al desactivar una asignatura, su campo ofertada debe ser false
	@Requisitos({ "RF1.1", "RF1.3" })
	@Test
	public void testDesactivarAsignaturas() throws SecretariaException, SecretariaIOException {
		gestionAsignaturas.importaAsignaturas("./DATOS/asignaturas.xlsx");
		Asignatura as = gestionAsignaturas.listarAsignaturas().get(0);
		int x = 1;
		while (!as.getOfertada()) {
			as = gestionAsignaturas.listarAsignaturas().get(x);
			x++;
		}
		int codigo = as.getCodigo();
		int titu = as.getTitulacion().getCodigo();

		gestionAsignaturas.desactivarAsignatura(codigo, titu);

		assertFalse("Error al desactivar la asignatura",
				gestionAsignaturas.obtenerAsignatura(codigo, titu).getOfertada());
	}

	// Al desactivar una asignatura que no existe, lanza excepcion
	@Requisitos({ "RF1.1", "RF1.3" })
	@Test(expected = SecretariaException.class)
	public void testDesactivarAsignaturaInexsistente() throws SecretariaException, SecretariaIOException {
		gestionAsignaturas.importaAsignaturas("./DATOS/asignaturas.xlsx");
		gestionAsignaturas.desactivarAsignatura(0, 0);
	}

	// Al desactivar una asignatura ya desactivada, lanza excepcion
	@Requisitos({ "RF1.1", "RF1.3" })
	@Test(expected = SecretariaException.class)
	public void testDesactivarAsignaturaYaDesactivada() throws SecretariaException, SecretariaIOException {
		gestionAsignaturas.importaAsignaturas("./DATOS/asignaturas.xlsx");
		Asignatura as = gestionAsignaturas.listarAsignaturas().get(0);
		int x = 1;
		while (!as.getOfertada()) {
			as = gestionAsignaturas.listarAsignaturas().get(x);
			x++;
		}
		int codigo = as.getCodigo();
		int titu = as.getTitulacion().getCodigo();

		gestionAsignaturas.desactivarAsignatura(codigo, titu);
		gestionAsignaturas.desactivarAsignatura(codigo, titu);
	}

	@Requisitos({ "RF1.4" })
	@Test
	public void defineGrupoAsignaturas() throws SecretariaException {
		
	}

}
