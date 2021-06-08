package es.uma.informatica.sii.web.tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ PeticionesIntegracion.class, AlumnosIntegracion.class, AsignaturasIntegracion.class,
		MatriculasIntegracion.class, AsignacionIntegracion.class })
public class SuiteIT {
	@BeforeClass
	public static void before() {
		BaseDeDatos.inicializar();
	}

	@AfterClass
	public static void after() {
		BaseDeDatos.cerrar();
	}
}
