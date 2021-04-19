package es.uma.informatica.sii.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.uma.informatica.sii.ejb.exceptions.SecretariaException;
import es.uma.informatica.sii.entities.Clase;
import es.uma.informatica.sii.entities.ClasePK;
import es.uma.informatica.sii.entities.Grupo;

@Stateless
public class AsignacionImpl implements GestionAsignacion {

	@PersistenceContext(name = "grupoE")
	private EntityManager em;

	@Override
	public void asignaGruposAlumnos() throws SecretariaException {
		//TODO

	}

	@Override
	public void creaGrupo(Grupo g) throws SecretariaException {
		Grupo gr = em.find(Grupo.class, g.getId());
		if (gr != null) {
			throw new SecretariaException("Se ha intentado crear un grupo que ya existia");
		}
		em.persist(g);

	}

	@Override
	public void borraGrupo(String id) throws SecretariaException {
		Grupo gr = em.find(Grupo.class, id);
		if (gr == null) {
			throw new SecretariaException("Se ha intentado borrar un grupo que no existe");
		}
		em.remove(gr);

	}

	@Override
	public void creaClase(Clase c) throws SecretariaException {
		ClasePK cpk = new ClasePK();
		cpk.setDia(c.getDia());
		cpk.setHora_inicio(c.getHora_inicio());
		cpk.setGrupo(c.getGrupo().getId());
		Clase cl = em.find(Clase.class, cpk);
		if (cl != null) {
			throw new SecretariaException("Se ha intentado crear una clase que ya existia");
		}
		em.persist(c);

	}

	@Override
	public void borraClase(String dia, String hora_inicio, String id_grupo) throws SecretariaException {
		ClasePK cpk = new ClasePK();
		cpk.setDia(dia);
		cpk.setHora_inicio(hora_inicio);
		cpk.setGrupo(id_grupo);
		Clase cl = em.find(Clase.class, cpk);
		if (cl == null) {
			throw new SecretariaException("Se ha intentado borrar una clase que no existe");
		}
		em.remove(cpk);
	}

}
