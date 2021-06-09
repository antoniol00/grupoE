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
import es.uma.informatica.sii.ejb.GestionAlumnos;
import es.uma.informatica.sii.ejb.GestionAsignacion;
import es.uma.informatica.sii.ejb.GestionAsignaturas;
import es.uma.informatica.sii.ejb.GestionMatriculas;
import es.uma.informatica.sii.ejb.exceptions.SecretariaException;
import es.uma.informatica.sii.ejb.exceptions.SecretariaIOException;
import es.uma.informatica.sii.entities.Asigna_grupos;
import es.uma.informatica.sii.entities.Clase;
import es.uma.informatica.sii.entities.Grupo;
import es.uma.informatica.sii.entities.Matricula;

public class PruebaAsignacion {

	private static final String ALUMNOS_EJB = "java:global/classes/AlumnosImpl";
	private static final String ASIGNACION_EJB = "java:global/classes/AsignacionImpl";
	private static final String ASIGNATURAS_EJB = "java:global/classes/AsignaturasImpl";
	private static final String MATRICULAS_EJB = "java:global/classes/MatriculasImpl";

	private GestionAlumnos gestionAlumnos;
	private GestionAsignacion gestionAsignacion;
	private GestionAsignaturas gestionAsignaturas;
	private GestionMatriculas gestionMatriculas;

	@Before
	public void setup() throws NamingException, IOException {
		gestionAlumnos = (GestionAlumnos) SuiteTest.ctx.lookup(ALUMNOS_EJB);
		gestionAsignacion = (GestionAsignacion) SuiteTest.ctx.lookup(ASIGNACION_EJB);
		gestionAsignaturas = (GestionAsignaturas) SuiteTest.ctx.lookup(ASIGNATURAS_EJB);
		gestionMatriculas = (GestionMatriculas) SuiteTest.ctx.lookup(MATRICULAS_EJB);
		BaseDatos.inicializaBaseDatos("grupoETest");
	}

	@Requisitos({ "RF5.1" })
	@Test
	public void testAvisoColisionesCorrecto() throws SecretariaException, SecretariaIOException {
		gestionAlumnos.importaAlumnos("./DATOS/alumnos.csv"); // importo alumnos
		gestionAlumnos.importaExpedientes("./DATOS/alumnos.csv"); // importo expedientes
		gestionMatriculas.importaMatriculas("./DATOS/alumnos.csv"); // importo matriculas
		gestionAsignaturas.importaAsignaturas("./DATOS/asignaturas.xlsx"); // importo asignaturas
		gestionAsignacion.asignaGruposAlumnos(); // realizo asignacion

		int x = 0;
		Matricula m = gestionMatriculas.listaMatriculas("ASC").get(x);
		while (!m.isNuevo_ingreso()) {
			x++;
			m = gestionMatriculas.listaMatriculas("ASC").get(x);
		}
		assertFalse(gestionAsignacion.ColisionesHorario(m.getNumero_archivo()));

	}

	@Requisitos({ "RF5.1" })
	@Test(expected = SecretariaException.class)
	public void testAvisoColisionesMatriculaIncorrecta() throws SecretariaException, SecretariaIOException {
		gestionAlumnos.importaAlumnos("./DATOS/alumnos.csv"); // importo alumnos
		gestionAlumnos.importaExpedientes("./DATOS/alumnos.csv"); // importo expedientes
		gestionMatriculas.importaMatriculas("./DATOS/alumnos.csv"); // importo matriculas
		gestionAsignaturas.importaAsignaturas("./DATOS/asignaturas.xlsx"); // importo asignaturas
		gestionAsignacion.asignaGruposAlumnos(); // realizo asignacion

		gestionAsignacion.ColisionesHorario(-1);

	}

	// comprueba que la tabla asignacion se ha rellenado correctamente
	@Requisitos({ "RF5.2", "RF5.3" })
	@Test
	public void testAsignarGrupo() throws SecretariaException, SecretariaIOException {
		gestionAlumnos.importaAlumnos("./DATOS/alumnos.csv"); // importo alumnos
		gestionAlumnos.importaExpedientes("./DATOS/alumnos.csv"); // importo expedientes
		gestionMatriculas.importaMatriculas("./DATOS/alumnos.csv"); // importo matriculas
		gestionAsignaturas.importaAsignaturas("./DATOS/asignaturas.xlsx"); // importo asignaturas
		gestionAsignacion.asignaGruposAlumnos(); // realizo asignacion

		assertNotEquals("Tabla no creada", 0, gestionAsignacion.listaAsignacionProvisional().size());
	}

	// comprueba que la tabla asignacion se ha rellenado correctamente
	@Requisitos({ "RF5.2", "RF5.3" })
	@Test
	public void testBorrarAsignacionGrupo() throws SecretariaException, SecretariaIOException {
		gestionAlumnos.importaAlumnos("./DATOS/alumnos.csv"); // importo alumnos
		gestionAlumnos.importaExpedientes("./DATOS/alumnos.csv"); // importo expedientes
		gestionMatriculas.importaMatriculas("./DATOS/alumnos.csv"); // importo matriculas
		gestionAsignaturas.importaAsignaturas("./DATOS/asignaturas.xlsx"); // importo asignaturas
		gestionAsignacion.asignaGruposAlumnos(); // realizo asignacion

		for (Asigna_grupos a : gestionAsignacion.listaAsignacionProvisional()) {
			gestionAsignacion.borraAsignacion(a);
		}
		assertTrue(gestionAsignacion.listaAsignacionProvisional().isEmpty());
	}

	@Requisitos({ "RF5.3" })
	@Test(expected = SecretariaException.class)
	public void testCrearGrupo() throws SecretariaException {
		Grupo gs1 = gestionAsignacion.obtenerGrupo("g0");
		gs1.setId("testGrupo");
		gestionAsignacion.creaGrupo(gs1);
		assertEquals(gs1, gestionAsignacion.obtenerGrupo("testGrupo"));
		gestionAsignacion.creaGrupo(gs1);
	}

	@Requisitos({ "RF5.3" })
	@Test(expected = SecretariaException.class)
	public void testBorrarGrupo() throws SecretariaException {
		Grupo gs1 = gestionAsignacion.obtenerGrupo("g0");
		gs1.setId("testGrupo");
		gestionAsignacion.creaGrupo(gs1);
		gestionAsignacion.borraGrupo("testGrupo");
		assertFalse(gestionAsignacion.listaGrupos().contains(gs1));
		gestionAsignacion.borraGrupo("testGrupo");
	}

	@Requisitos({ "RF5.3" })
	@Test(expected = SecretariaException.class)
	public void testCrearClase() throws SecretariaException, SecretariaIOException {
		gestionAsignaturas.importaAsignaturas("./DATOS/asignaturas.xlsx"); // importo asignaturas
		Clase c = new Clase();
		c.setDia("L");
		c.setHora_inicio("9");
		c.setHora_fin("10");
		c.setAsignatura(gestionAsignaturas.listarAsignaturas().get(0));
		c.setGrupo(gestionAsignacion.obtenerGrupo("g0"));
		gestionAsignacion.creaClase(c);
		assertFalse(gestionAsignacion.listaClases().isEmpty());
		gestionAsignacion.creaClase(c);
	}

	@Requisitos({ "RF5.3" })
	@Test(expected = SecretariaException.class)
	public void testBorrarClase() throws SecretariaException, SecretariaIOException {
		gestionAsignaturas.importaAsignaturas("./DATOS/asignaturas.xlsx"); // importo asignaturas
		Clase c = new Clase();
		c.setDia("L");
		c.setHora_inicio("9");
		c.setHora_fin("10");
		c.setAsignatura(gestionAsignaturas.listarAsignaturas().get(0));
		c.setGrupo(gestionAsignacion.obtenerGrupo("g0"));
		gestionAsignacion.creaClase(c);
		Clase t = gestionAsignacion.obtenerClase("L", "9", "g0");
		gestionAsignacion.borraClase(t.getDia(), t.getHora_inicio(), t.getGrupo().getId());
		assertTrue(gestionAsignacion.listaClases().isEmpty());
		gestionAsignacion.borraClase("L", "9", "g0");
	}
}