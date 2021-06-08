package es.uma.informatica.sii.web.tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import es.uma.informatica.sii.entities.Alumno;
import es.uma.informatica.sii.entities.Asignatura;
import es.uma.informatica.sii.entities.AsignaturaPK;
import es.uma.informatica.sii.entities.Grupo;
import es.uma.informatica.sii.entities.Grupos_asig;
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

	public static void modificaAsignacion() {
		em.getTransaction().begin();

		Grupos_asig asig = new Grupos_asig();
		AsignaturaPK asigpk = new AsignaturaPK();
		asigpk.setCodigo(105);
		asigpk.setTitulacion(1041);
		asig.setAsignatura(em.find(Asignatura.class, asigpk));
		asig.setGrupo(em.find(Grupo.class, "II1B"));
		asig.setCurso("20/21");
		em.persist(asig);

		Grupos_asig asig2 = new Grupos_asig();
		AsignaturaPK asigpk2 = new AsignaturaPK();
		asigpk2.setCodigo(105);
		asigpk2.setTitulacion(1041);
		asig2.setAsignatura(em.find(Asignatura.class, asigpk2));
		asig2.setGrupo(em.find(Grupo.class, "II1C"));
		asig2.setCurso("20/21");
		em.persist(asig2);

		em.getTransaction().commit();
	}
}
