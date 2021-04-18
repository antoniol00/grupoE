package es.uma.informatica.sii.ejb;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.uma.informatica.sii.ejb.exceptions.SecretariaException;

@Stateless
public class PeticionesImpl implements GestionPeticiones {

	private static final Logger LOG = Logger.getLogger(PeticionesImpl.class.getCanonicalName());

	@PersistenceContext(name = "grupoE")
	private EntityManager em;
	
	@Override
	public void creaIncidencia(String i) throws SecretariaException {
		// TODO Auto-generated method stub

	}

}
