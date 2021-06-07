package es.uma.informatica.sii.web.tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import es.uma.informatica.sii.entities.Alumno;
import es.uma.informatica.sii.entities.Peticion;

public class BaseDeDatos {

	private static EntityManagerFactory emf;
	private static EntityManager em;

	public static void inicializar() {
		emf = Persistence.createEntityManagerFactory("grupoEPI");
		em = emf.createEntityManager();
	}

	public static void cerrar() {
		em.close();
		emf.close();
	}

	// PeticionesIntegracion.class
	public static void vaciaPeticiones() {
		em.getTransaction().begin();

		TypedQuery<Peticion> query = em.createQuery("select a from Peticion a", Peticion.class);
		for (Peticion p : query.getResultList()) {
			em.remove(p);
		}

		em.getTransaction().commit();
	}

	public static void vaciaAlumnos() {
		em.getTransaction().begin();

		TypedQuery<Alumno> query = em.createQuery("select a from Alumno a", Alumno.class);
		for (Alumno a : query.getResultList()) {
			em.remove(a);
		}

		em.getTransaction().commit();
	}

	public static void insertaAlumno() {
		em.getTransaction().begin();

		Alumno al = new Alumno();
		al.setDni("11111111A");
		al.setEmail_institucional("uma@uma.es");
		al.setNombre_completo("uma");
		em.persist(al);

		em.getTransaction().commit();
	}
}
