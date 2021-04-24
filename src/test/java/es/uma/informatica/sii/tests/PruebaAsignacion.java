package es.uma.informatica.sii.tests;

import static org.junit.Assert.assertFalse;
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

		assertTrue("Tabla no creada", gestionAsignacion.listaAsignacionProvisional().size() != 0);
	}
}