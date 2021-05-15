package es.uma.informatica.sii.web.tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BaseDeDatos {

	public static void inicializar(String unidadPersistencia) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(unidadPersistencia);
		EntityManager em = emf.createEntityManager();

		em.close();
		emf.close();

	}
}
