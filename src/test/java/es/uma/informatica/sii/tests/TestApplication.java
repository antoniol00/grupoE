package es.uma.informatica.sii.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.uma.informatica.sii.anotaciones.Requisitos;
import es.uma.informatica.sii.ejb.GestionAlumnos;
import es.uma.informatica.sii.ejb.GestionAsignacion;
import es.uma.informatica.sii.ejb.GestionAsignaturas;
import es.uma.informatica.sii.ejb.GestionMatriculas;
import es.uma.informatica.sii.ejb.GestionPeticiones;
import es.uma.informatica.sii.ejb.exceptions.SecretariaException;
import es.uma.informatica.sii.entities.Alumno;

public class TestApplication {

	private static final Logger LOG = Logger.getLogger(TestApplication.class.getCanonicalName());

	private static final String GLASSFISH_CONFIGI_FILE_PROPERTY = "org.glassfish.ejb.embedded.glassfish.configuration.file";
	private static final String CONFIG_FILE = "target/test-classes/META-INF/domain.xml";
	private static final String ALUMNOS_EJB = "java:global/classes/AlumnosImpl";
	private static final String ASIGNACION_EJB = "java:global/classes/AsignacionImpl";
	private static final String ASIGNATURAS_EJB = "java:global/classes/AsignaturasImpl";
	private static final String MATRICULAS_EJB = "java:global/classes/MatriculasImpl";
	private static final String PETICIONES_EJB = "java:global/classes/PeticionesImpl";

	private static EJBContainer ejbContainer;
	private static Context ctx;

	private GestionAlumnos gestionAlumnos;
	private GestionAsignacion gestionAsignacion;
	private GestionAsignaturas gestionAsignaturas;
	private GestionMatriculas gestionMatriculas;
	private GestionPeticiones gestionPeticiones;

	@BeforeClass
	public static void setUpClass() {
		Properties properties = new Properties();
		properties.setProperty(GLASSFISH_CONFIGI_FILE_PROPERTY, CONFIG_FILE);
		ejbContainer = EJBContainer.createEJBContainer(properties);
		ctx = ejbContainer.getContext();
	}

	@Before
	public void setup() throws NamingException, IOException {
		gestionAlumnos = (GestionAlumnos) ctx.lookup(ALUMNOS_EJB);
		gestionAsignacion = (GestionAsignacion) ctx.lookup(ASIGNACION_EJB);
		gestionAsignaturas = (GestionAsignaturas) ctx.lookup(ASIGNATURAS_EJB);
		gestionMatriculas = (GestionMatriculas) ctx.lookup(MATRICULAS_EJB);
		gestionPeticiones = (GestionPeticiones) ctx.lookup(PETICIONES_EJB);
		BaseDatos.inicializaBaseDatos("grupoETest");
	}

	@Requisitos({ "RF2.1" })
	@Test
	public void importarAlumnos() throws SecretariaException, IOException, ParseException {
		gestionAlumnos.importaAlumnos("./DATOS/alumnos.csv");
		assertTrue("Error",gestionAlumnos.obtenerListaAlumnos().size()==1508);
	}

	//de prueba
	@Requisitos({ "RF1", "RF7" })
	@Test
	public void test2() {
		assertEquals("es incorrecto", 0, 0);
	}
	
	//de prueba
	@Requisitos({ "RF1", "RF7" })
	@Test
	public void add_SingleNumber_ReturnsSameNumber(){
	
		int actual = 0;
		assertEquals(0, actual);
	}	
	
	@AfterClass
	public static void tearDownClass() {
		if (ejbContainer != null) {
			ejbContainer.close();
		}
	}
}
