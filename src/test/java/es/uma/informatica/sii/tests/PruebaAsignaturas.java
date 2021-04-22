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
import es.uma.informatica.sii.entities.Asignatura;

public class PruebaAsignaturas {

	private static final String ASIGNATURAS_EJB = "java:global/classes/AsignaturasImpl";

	private GestionAsignaturas gestionAsignaturas;

	@Before
	public void setup() throws NamingException, IOException {
		gestionAsignaturas = (GestionAsignaturas) SuiteTest.ctx.lookup(ASIGNATURAS_EJB);
		BaseDatos.inicializaBaseDatos("grupoETest");
	}

	@Requisitos({ "RF1.1" })
	@Test
	public void importarAsignaturas() throws SecretariaException, IOException, ParseException {
		gestionAsignaturas.importaAsignaturas("./DATOS/asignaturas.xlsx");
		assertTrue("Error al importar asignaturas", gestionAsignaturas.listarAsignaturas().size() != 0);
	}

	@Requisitos({ "RF1.2" })
	@Test
	public void modificarAsignaturas() throws SecretariaException, IOException, ParseException {
		gestionAsignaturas.importaAsignaturas("./DATOS/asignaturas.xlsx");
		Asignatura asig = gestionAsignaturas.listarAsignaturas().get(0);
		asig.setCurso(8);
		int codigo = asig.getCodigo();
		int titu = asig.getTitulacion().getCodigo();
		gestionAsignaturas.modificarAsignatura(codigo, titu, asig);
		assertEquals("Error al modificar la asignatura", asig.getCurso(),
				gestionAsignaturas.obtenerAsignatura(codigo, titu).getCurso());
	}

	@Requisitos({ "RF1.3" })
	@Test
	public void borrarAsignaturas() throws SecretariaException, IOException, ParseException {
		gestionAsignaturas.importaAsignaturas("./DATOS/asignaturas.xlsx");
		Asignatura asig = gestionAsignaturas.listarAsignaturas().get(5);
		int codigo = asig.getCodigo();
		int titu = asig.getTitulacion().getCodigo();
		gestionAsignaturas.borrarAsignatura(codigo, titu);
		assertFalse("Error al borrar la asignatura", gestionAsignaturas.listarAsignaturas().contains(asig));
	}

	@Requisitos({ "RF1.4" })
	@Test
	public void defineGrupoAsignaturas() throws SecretariaException, IOException, ParseException {

	}

}
