package es.uma.informatica.sii.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import es.uma.informatica.sii.ejb.exceptions.SecretariaException;
import es.uma.informatica.sii.entities.Peticion;
import es.uma.informatica.sii.entities.PeticionPK;

@Stateless
public class PeticionesImpl implements GestionPeticiones {

	@PersistenceContext(name = "grupoE")
	private EntityManager em;

	@Override
	public void creaIncidencia(Peticion p) throws SecretariaException {
		PeticionPK ppk = new PeticionPK();
		ppk.setAlumno(p.getAlumno().getDni());
		ppk.setDate(p.getDate());
		Peticion pe = em.find(Peticion.class, ppk);
		if (pe != null) {
			throw new SecretariaException("Se ha intentado crear una incidencia que ya existia");
		}
		em.merge(p);
	}

	@Override
	public void editaIncidencia(Date date, String dni, Peticion p) throws SecretariaException {
		PeticionPK ppk = new PeticionPK();
		ppk.setAlumno(dni);
		ppk.setDate(date);
		Peticion pe = em.find(Peticion.class, ppk);
		if (pe == null) {
			throw new SecretariaException("Se ha intentado editar una incidencia que no esta en la base de datos");
		}
		em.merge(p);

	}

	@Override
	public void borraIncidencia(Date date, String dni) throws SecretariaException {
		PeticionPK ppk = new PeticionPK();
		ppk.setAlumno(dni);
		ppk.setDate(date);
		Peticion pe = em.find(Peticion.class, ppk);
		if (pe == null) {
			throw new SecretariaException("Se ha intentado borrar una incidencia que no esta en la base de datos");
		}
		em.remove(pe);

	}

	// METODOS AUXILIARES
	@Override
	public Peticion obtenerPeticion(Date date, String dni) throws SecretariaException {
		PeticionPK ppk = new PeticionPK();
		ppk.setAlumno(dni);
		ppk.setDate(date);
		Peticion pe = em.find(Peticion.class, ppk);
		if (pe == null) {
			throw new SecretariaException("Se ha intentado borrar una incidencia que no esta en la base de datos");
		}
		return pe;
	}

	@Override
	public List<Peticion> listaPeticiones() {
		TypedQuery<Peticion> query = em.createQuery("select p from Peticion p", Peticion.class);
		return query.getResultList();
	}

}
