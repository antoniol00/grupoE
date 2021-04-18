package es.uma.informatica.sii.ejb;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.uma.informatica.sii.ejb.exceptions.SecretariaException;

@Stateless
public class AsignacionImpl implements GestionAsignacion {

	private static final Logger LOG = Logger.getLogger(AsignacionImpl.class.getCanonicalName());

	@PersistenceContext(name = "grupoE")
	private EntityManager em;
	
	@Override
	public void asignaGruposAlumnos() throws SecretariaException {
		// TODO Auto-generated method stub

	}

}
