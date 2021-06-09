package es.uma.informatica.sii.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import es.uma.informatica.sii.anotaciones.Requisitos;
import es.uma.informatica.sii.ejb.GestionAsignacion;
import es.uma.informatica.sii.ejb.GestionAsignaturas;
import es.uma.informatica.sii.ejb.exceptions.SecretariaException;
import es.uma.informatica.sii.ejb.exceptions.SecretariaIOException;
import es.uma.informatica.sii.entities.Asignatura;
import es.uma.informatica.sii.entities.Grupo;
import es.uma.informatica.sii.entities.Grupos_asig;

public class PruebaAsignaturas {

	private static final String ASIGNATURAS_EJB = "java:global/classes/AsignaturasImpl";
	private static final String ASIGNACION_EJB = "java:global/classes/AsignacionImpl";

	private GestionAsignaturas gestionAsignaturas;
	private GestionAsignacion gestionAsignacion;

	@Before
	public void setup() throws NamingException, IOException, SecretariaIOException, SecretariaException {
		gestionAsignaturas = (GestionAsignaturas) SuiteTest.ctx.lookup(ASIGNATURAS_EJB);
		gestionAsignacion = (GestionAsignacion) SuiteTest.ctx.lookup(ASIGNACION_EJB);
		BaseDatos.inicializaBaseDatos("grupoETest");
		gestionAsignaturas.importaAsignaturas("./DATOS/asignaturas.xlsx");
	}

	// Importar asignaturas debe devolver una lista no vacia
	@Requisitos({ "RF1.1" })
	@Test
	public void testImportarAsignaturasListaNoVacia() throws SecretariaIOException, SecretariaException {
		assertNotEquals("Error al importar asignaturas", 0, gestionAsignaturas.listarAsignaturas().size());
	}

	// Importar asignaturas debe devolver una lista no vacia
	@Requisitos({ "RF1.1" })
	@Test
	public void testImportarOptativasListaNoVacia() throws SecretariaIOException, SecretariaException {
		gestionAsignaturas.importaOptativas("./DATOS/asignaturas.xlsx");
		assertNotEquals("Error al importar asignaturas", 0, gestionAsignaturas.listarAsignaturas().size());
	}

	// Importar asignaturas debe devolver una lista con 364 entradas
	@Requisitos({ "RF1.1" })
	@Test
	public void testImportarAsignaturasTamanoCorrecto() throws SecretariaIOException, SecretariaException {
		assertEquals("Error al importar asignaturas", 364, gestionAsignaturas.listarAsignaturas().size());
	}

	// Al modificar una asignatura se modifican los cambios especificados en la bbdd
	@Requisitos({ "RF1.2" })
	@Test
	public void testModificarAsignaturas() throws SecretariaException, SecretariaIOException {
		Asignatura asig = gestionAsignaturas.listarAsignaturas().get(0);
		asig.setCurso(8);
		int codigo = asig.getCodigo();
		int titu = asig.getTitulacion().getCodigo();
		gestionAsignaturas.modificarAsignatura(codigo, titu, asig);
		assertEquals("Error al modificar la asignatura", asig.getCurso(),
				gestionAsignaturas.obtenerAsignatura(codigo, titu).getCurso());
	}

	// Al modificar una asignatura que no existe, lanza excepcion
	@Requisitos({ "RF1.2" })
	@Test(expected = SecretariaException.class)
	public void testModificarAlumnoInexsistente() throws SecretariaException, SecretariaIOException {
		gestionAsignaturas.modificarAsignatura(-1, -1, gestionAsignaturas.listarAsignaturas().get(0));

	}

	// Borrar todas las asignaturas queda con una lista vacia
	@Requisitos({ "RF1.3" })
	@Test
	public void borrarTodasAsignaturas() throws SecretariaException, SecretariaIOException {
		for (Asignatura as : gestionAsignaturas.listarAsignaturas())
			gestionAsignaturas.borrarAsignatura(as.getCodigo(), as.getTitulacion().getCodigo());
		assertEquals("Error al borrar la asignatura", 0, gestionAsignaturas.listarAsignaturas().size());
	}

	// Al borrar una asignatura que no existe, lanza excepcion
	@Requisitos({ "RF1.3" })
	@Test(expected = SecretariaException.class)
	public void testBorrarAsignaturaInexsistente() throws SecretariaException, SecretariaIOException {
		gestionAsignaturas.modificarAsignatura(-1, -1, gestionAsignaturas.listarAsignaturas().get(0));

	}

	// Al desactivar una asignatura, su campo ofertada debe ser false
	@Requisitos({ "RF1.3" })
	@Test
	public void testDesactivarAsignaturas() throws SecretariaException, SecretariaIOException {
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
	@Requisitos({ "RF1.3" })
	@Test(expected = SecretariaException.class)
	public void testDesactivarAsignaturaInexsistente() throws SecretariaException, SecretariaIOException {
		gestionAsignaturas.desactivarAsignatura(-1, -1);
	}

	// Al desactivar una asignatura ya desactivada, lanza excepcion
	@Requisitos({ "RF1.3" })
	@Test(expected = SecretariaException.class)
	public void testDesactivarAsignaturaYaDesactivada() throws SecretariaException, SecretariaIOException {
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

	// Definimos un grupo para una asignatura.
	@Requisitos({ "RF1.4" })
	@Test
	public void defineGrupoAsignaturas() throws SecretariaException, SecretariaIOException {
		Asignatura as = gestionAsignaturas.listarAsignaturas().get(0);
		Grupo g = gestionAsignacion.listaGrupos().get(1);
		assertTrue("Lista no vacia de gruposXAsig", gestionAsignaturas.listarGrupoXAsignaturas().isEmpty());

		gestionAsignaturas.definirGrupos(as.getCodigo(), as.getTitulacion().getCodigo(), g.getId(), "22/23");

		assertFalse("Lista vacia de gruposXAsig", gestionAsignaturas.listarGrupoXAsignaturas().isEmpty());

		Grupos_asig ga = new Grupos_asig();
		ga.setAsignatura(as);
		ga.setCurso("22/23");
		ga.setGrupo(g);

		assertTrue("Lista no contiene grupo creado", gestionAsignaturas.listarGrupoXAsignaturas().contains(ga));

	}

	@Requisitos({ "RF1.4" })
	@Test
	public void borrarDefinicionGrupoAsignatura() throws SecretariaException, SecretariaIOException {
		Asignatura as = gestionAsignaturas.listarAsignaturas().get(0);
		Grupo g = gestionAsignacion.listaGrupos().get(1);

		gestionAsignaturas.definirGrupos(as.getCodigo(), as.getTitulacion().getCodigo(), g.getId(), "22/23");

		Grupos_asig ga = new Grupos_asig();
		ga.setAsignatura(as);
		ga.setCurso("22/23");
		ga.setGrupo(g);

		gestionAsignaturas.borrarDefinirGrupos(ga);

		assertTrue("Lista contiene grupo borrado", gestionAsignaturas.listarGrupoXAsignaturas().isEmpty());

	}

}
