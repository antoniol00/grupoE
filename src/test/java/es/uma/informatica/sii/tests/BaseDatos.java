package es.uma.informatica.sii.tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BaseDatos {
	public static void inicializaBaseDatos(String nombreUnidadPersistencia) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(nombreUnidadPersistencia);
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		// INICIALIZACION BASE DE DATOS

		em.getTransaction().commit();

		em.close();
		emf.close();
	}
}
