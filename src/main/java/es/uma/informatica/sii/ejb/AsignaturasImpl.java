package es.uma.informatica.sii.ejb;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.uma.informatica.sii.ejb.exceptions.SecretariaException;

@Stateless
public class AsignaturasImpl implements GestionAsignaturas {

	private static final Logger LOG = Logger.getLogger(AsignaturasImpl.class.getCanonicalName());

	@PersistenceContext(name = "grupoE")
	private EntityManager em;
	
	@Override
	public void importaAsignaturas() throws SecretariaException {
		// TODO Auto-generated method stub

	}

	@Override
	public void modificarAsignatura(String codigo, String titulacion) throws SecretariaException {
		// TODO Auto-generated method stub

	}

	@Override
	public void borrarAsignatura(String codigo, String titulacion) throws SecretariaException {
		// TODO Auto-generated method stub

	}

	@Override
	public void definirGrupos(String codigo, String titulacion) throws SecretariaException {
		// TODO Auto-generated method stub

	}

}
