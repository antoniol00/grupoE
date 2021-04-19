package es.uma.informatica.sii.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.uma.informatica.sii.ejb.exceptions.SecretariaException;
import es.uma.informatica.sii.entities.Peticion;

@Stateless
public class PeticionesImpl implements GestionPeticiones {

	@PersistenceContext(name = "grupoE")
	private EntityManager em;

	@Override
	public void creaIncidencia(Peticion p) throws SecretariaException {
		Peticion pe = em.find(Peticion.class, p.getId());
		if (pe == null) {
			em.persist(p);
		} else {
			throw new SecretariaException("Se ha intentado crear una peitcion que ya existia en la base de datos");
		}

	}

	@Override
	public void editaIncidencia(Integer id, Peticion p) throws SecretariaException {
		Peticion pe = em.find(Peticion.class, id);
		if (pe == null) {
			throw new SecretariaException("Se ha intentado editar una incidencia que no se haya en la base de datos");
		}
		em.merge(p);

	}

	@Override
	public void borraIncidencia(Integer id) throws SecretariaException {
		Peticion pe = em.find(Peticion.class, id);
		if (pe == null) {
			throw new SecretariaException("Se ha intentado borrar una incidencia que no se haya en la base de datos");
		}
		em.remove(pe);

	}

}
