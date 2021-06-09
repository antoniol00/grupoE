package es.uma.informatica.sii.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import es.uma.informatica.sii.anotaciones.Requisitos;
import es.uma.informatica.sii.ejb.GestionAlumnos;
import es.uma.informatica.sii.ejb.GestionMatriculas;
import es.uma.informatica.sii.ejb.exceptions.SecretariaException;
import es.uma.informatica.sii.ejb.exceptions.SecretariaIOException;
import es.uma.informatica.sii.entities.Matricula;

public class PruebaMatriculas {

	private static final String ALUMNOS_EJB = "java:global/classes/AlumnosImpl";
	private static final String MATRICULAS_EJB = "java:global/classes/MatriculasImpl";

	private GestionAlumnos gestionAlumnos;
	private GestionMatriculas gestionMatriculas;

	@Before
	public void setup() throws NamingException, IOException, SecretariaIOException, SecretariaException {
		gestionAlumnos = (GestionAlumnos) SuiteTest.ctx.lookup(ALUMNOS_EJB);
		gestionMatriculas = (GestionMatriculas) SuiteTest.ctx.lookup(MATRICULAS_EJB);
		BaseDatos.inicializaBaseDatos("grupoETest");
		gestionAlumnos.importaAlumnos("./DATOS/alumnos.csv");
		gestionAlumnos.importaExpedientes("./DATOS/alumnos.csv");
		gestionMatriculas.importaMatriculas("./DATOS/alumnos.csv");
	}

	@Requisitos({ "RF3.1" })
	@Test
	public void testImportarMatriculas() throws SecretariaIOException, SecretariaException {
		String filter = "ASC";
		assertNotEquals("Error al importar matriculas", 0, gestionMatriculas.listaMatriculas(filter).size());
		assertEquals("Error al importar matriculas", 1508, gestionMatriculas.listaMatriculas(filter).size());
	}

	@Requisitos({ "RF3.2" })
	@Test
	public void tesListarMatriculasFiltrosDiferentes() throws SecretariaException, SecretariaIOException {
		String[] filters = { "DATE", "ASC", "DES", "EXP" };
		List<List<Matricula>> aam = new ArrayList<>();
		for (String f : filters) {
			aam.add(gestionMatriculas.listaMatriculas(f));
		}
		boolean iguales = false;
		for (int x = 0; x < aam.size() - 1; x++) {
			if (aam.get(x).equals(aam.get(x + 1))) {
				iguales = true;
			}
		}
		assertFalse("diferentes filtros crean mismas listas", iguales);
	}

	@Requisitos({ "RF3.2" })
	@Test
	public void tesListarMatriculasNoCreaListasVacias() throws SecretariaException, SecretariaIOException {
		String[] filters = { "DATE", "ASC", "DES", "EXP" };
		for (String f : filters) {
			assertFalse("lista vacia al ordenar", gestionMatriculas.listaMatriculas(f).isEmpty());
		}
	}

	@Requisitos({ "RF3.2" })
	@Test(expected = SecretariaException.class)
	public void tesListarMatriculasConFiltroNoValido() throws SecretariaException, SecretariaIOException {
		gestionMatriculas.listaMatriculas("x");
	}
}
