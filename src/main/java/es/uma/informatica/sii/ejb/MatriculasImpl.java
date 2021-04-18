package es.uma.informatica.sii.ejb;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.uma.informatica.sii.ejb.exceptions.SecretariaException;

@Stateless
public class MatriculasImpl implements GestionMatriculas {

	private static final Logger LOG = Logger.getLogger(MatriculasImpl.class.getCanonicalName());

	@PersistenceContext(name = "grupoE")
	private EntityManager em;
	
	
	@Override
	public void importaMatriculas() throws SecretariaException {
		// TODO Auto-generated method stub

	}

	@Override
	public void listaMatriculas(String filter) throws SecretariaException {
		// TODO Auto-generated method stub

	}

}
