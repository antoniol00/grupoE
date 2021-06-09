package es.uma.informatica.sii.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
import es.uma.informatica.sii.entities.Alumno;
import es.uma.informatica.sii.entities.Asigna_grupos;
import es.uma.informatica.sii.entities.Expediente;

public class PruebaAlumnos {

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

	// Importar alumnos debe devolver una lista no vacia de 1508 entradas
	@Requisitos({ "RF2.1" })
	@Test
	public void testImportarAlumnosLista() throws SecretariaException, SecretariaIOException {
		gestionAlumnos.importaAlumnos("./DATOS/alumnos.csv");
		assertNotEquals("Error al importar alumnos", 0, gestionAlumnos.obtenerListaAlumnos().size());
		assertEquals("Error al importar alumnos", 1508, gestionAlumnos.obtenerListaAlumnos().size());
	}

	// Buscar alumno existente no debe devolver error
	@Requisitos({ "RF2.1" })
	public void testObtenerAlumnoExistente() throws SecretariaException, SecretariaIOException {
		gestionAlumnos.importaAlumnos("./DATOS/alumnos.csv"); // importo alumnos
		Alumno a = gestionAlumnos.obtenerListaAlumnos().get(0);
		Alumno e = gestionAlumnos.obtenerAlumno(a.getDni());
		assertEquals("DNI no coincidiente", a.getDni(), e.getDni());
	}

	// Buscar alumno no existente debe devolver excepcion
	@Requisitos({ "RF2.1" })
	@Test(expected = SecretariaException.class)
	public void testObtenerAlumnoNoExistente() throws SecretariaException, SecretariaIOException {
		Alumno a = gestionAlumnos.obtenerAlumno("X");
	}

	// Importar expedientes debe devolver una lista no vacia de 1508 entradas
	@Requisitos({ "RF2.1" })
	@Test
	public void testImportarExpedientesLista() throws SecretariaException, SecretariaIOException {
		gestionAlumnos.importaAlumnos("./DATOS/alumnos.csv");
		gestionAlumnos.importaExpedientes("./DATOS/alumnos.csv");
		assertNotEquals("Error al importar expedientes", 0, gestionAlumnos.obtenerListaExpedientes().size());
		assertEquals("Error al importar expedientes", 1508, gestionAlumnos.obtenerListaExpedientes().size());
	}

	// Buscar expediente existente no debe devolver error
	@Requisitos({ "RF2.1" })
	public void testObtenerExpedienteExistente() throws SecretariaException, SecretariaIOException {
		gestionAlumnos.importaAlumnos("./DATOS/alumnos.csv");
		gestionAlumnos.importaExpedientes("./DATOS/alumnos.csv");
		Expediente ex = gestionAlumnos.obtenerListaExpedientes().get(0);
		Expediente e = gestionAlumnos.obtenerExpediente(ex.getNumero());
		assertEquals("Numero de exp no coincidiente", ex.getNumero(), e.getNumero());
	}

	// Buscar expediente no existente debe devolver excepcion
	@Requisitos({ "RF2.1" })
	@Test(expected = SecretariaException.class)
	public void testObtenerExpedienteNoExistente() throws SecretariaException, SecretariaIOException {
		Expediente ex = gestionAlumnos.obtenerExpediente(0);
	}

	// Borrar alumno existente
	@Requisitos({ "RF2.1" })
	public void testBorraAlumnoExistente() throws SecretariaException, SecretariaIOException {
		gestionAlumnos.importaAlumnos("./DATOS/alumnos.csv");
		String dni = gestionAlumnos.obtenerListaAlumnos().get(0).getDni();
		gestionAlumnos.borraAlumno(gestionAlumnos.obtenerListaAlumnos().get(0));
		assertNotEquals(1508, gestionAlumnos.obtenerListaAlumnos().size());
	}

	// Al modificar un alumno se modifican los cambios especificados en la bbdd
	@Requisitos({ "RF2.2", "RF.4.2" })
	@Test
	public void testModificarGrupoAlumno() throws SecretariaException, SecretariaIOException {
		gestionAlumnos.importaAlumnos("./DATOS/alumnos.csv"); // importo alumnos
		gestionAlumnos.importaExpedientes("./DATOS/alumnos.csv"); // importo expedientes
		gestionMatriculas.importaMatriculas("./DATOS/alumnos.csv"); // importo matriculas
		gestionAsignaturas.importaAsignaturas("./DATOS/asignaturas.xlsx"); // importo asignaturas
		gestionAsignacion.asignaGruposAlumnos(); // realizo asignacion

		Asigna_grupos ag = gestionAsignacion.listaAsignacionProvisional().get(1000);
		String id_grupo_asignado = ag.getGrupo().getId();
		if (id_grupo_asignado.equals("g0"))
			id_grupo_asignado = "g1";
		gestionAsignacion.obtenerGrupo(id_grupo_asignado);

		gestionAsignacion.modificaGrupo(ag.getAsignatura().getCodigo(), ag.getAsignatura().getTitulacion().getCodigo(),
				ag.getMatricula().getCurso(), ag.getMatricula().getExpediente().getNumero(), ag.getGrupo().getId());

		assertEquals("Grupo no modificado: expected: " + id_grupo_asignado + " actual: "
				+ gestionAsignacion.obtieneAsignacion(
						ag.getAsignatura().getCodigo(), ag.getAsignatura().getTitulacion().getCodigo(),
						ag.getMatricula().getCurso(), ag.getMatricula().getExpediente().getNumero()).getGrupo().getId(),
				id_grupo_asignado,
				gestionAsignacion.obtieneAsignacion(ag.getAsignatura().getCodigo(),
						ag.getAsignatura().getTitulacion().getCodigo(), ag.getMatricula().getCurso(),
						ag.getMatricula().getExpediente().getNumero()).getGrupo().getId());

	}

	@Requisitos({ "RF.4.2" })
	@Test(expected = SecretariaException.class)
	public void testModificarGrupoAsignaturaInexsistente() throws SecretariaException, SecretariaIOException {
		gestionAlumnos.importaAlumnos("./DATOS/alumnos.csv"); // importo alumnos
		gestionAlumnos.importaExpedientes("./DATOS/alumnos.csv"); // importo expedientes
		gestionMatriculas.importaMatriculas("./DATOS/alumnos.csv"); // importo matriculas
		gestionAsignaturas.importaAsignaturas("./DATOS/asignaturas.xlsx"); // importo asignaturas
		gestionAsignacion.asignaGruposAlumnos(); // realizo asignacion

		Asigna_grupos ag = gestionAsignacion.listaAsignacionProvisional().get(100);
		String id_grupo_asignado = ag.getGrupo().getId();
		if (id_grupo_asignado.equals("g0"))
			id_grupo_asignado = "g1";
		gestionAsignacion.obtenerGrupo(id_grupo_asignado);

		gestionAsignacion.modificaGrupo(-1, ag.getAsignatura().getTitulacion().getCodigo(),
				ag.getMatricula().getCurso(), ag.getMatricula().getExpediente().getNumero(), ag.getGrupo().getId());
	}

	@Requisitos({ "RF.4.2" })
	@Test(expected = SecretariaException.class)
	public void testModificarGrupoMatriculaInexsistente() throws SecretariaException, SecretariaIOException {
		gestionAlumnos.importaAlumnos("./DATOS/alumnos.csv"); // importo alumnos
		gestionAlumnos.importaExpedientes("./DATOS/alumnos.csv"); // importo expedientes
		gestionMatriculas.importaMatriculas("./DATOS/alumnos.csv"); // importo matriculas
		gestionAsignaturas.importaAsignaturas("./DATOS/asignaturas.xlsx"); // importo asignaturas
		gestionAsignacion.asignaGruposAlumnos(); // realizo asignacion

		Asigna_grupos ag = gestionAsignacion.listaAsignacionProvisional().get(100);
		String id_grupo_asignado = ag.getGrupo().getId();
		if (id_grupo_asignado.equals("g0"))
			id_grupo_asignado = "g1";
		gestionAsignacion.obtenerGrupo(id_grupo_asignado);

		gestionAsignacion.modificaGrupo(ag.getAsignatura().getCodigo(), ag.getAsignatura().getTitulacion().getCodigo(),
				ag.getMatricula().getCurso(), -1, ag.getGrupo().getId());
	}

	@Requisitos({ "RF.4.2" })
	@Test(expected = SecretariaException.class)
	public void testModificarGrupoGrupoInexsistente() throws SecretariaException, SecretariaIOException {
		gestionAlumnos.importaAlumnos("./DATOS/alumnos.csv"); // importo alumnos
		gestionAlumnos.importaExpedientes("./DATOS/alumnos.csv"); // importo expedientes
		gestionMatriculas.importaMatriculas("./DATOS/alumnos.csv"); // importo matriculas
		gestionAsignaturas.importaAsignaturas("./DATOS/asignaturas.xlsx"); // importo asignaturas
		gestionAsignacion.asignaGruposAlumnos(); // realizo asignacion

		Asigna_grupos ag = gestionAsignacion.listaAsignacionProvisional().get(100);
		String id_grupo_asignado = ag.getGrupo().getId();
		if (id_grupo_asignado.equals("g0"))
			id_grupo_asignado = "g1";
		gestionAsignacion.obtenerGrupo(id_grupo_asignado);

		gestionAsignacion.modificaGrupo(ag.getAsignatura().getCodigo(), ag.getAsignatura().getTitulacion().getCodigo(),
				ag.getMatricula().getCurso(), ag.getMatricula().getExpediente().getNumero(), "X");
	}
}
