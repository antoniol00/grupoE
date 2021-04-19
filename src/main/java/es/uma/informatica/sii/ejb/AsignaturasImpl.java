package es.uma.informatica.sii.ejb;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.uma.informatica.sii.ejb.exceptions.SecretariaException;
import es.uma.informatica.sii.entities.Asignatura;
import es.uma.informatica.sii.entities.Grupos_asig;

@Stateless
public class AsignaturasImpl implements GestionAsignaturas {

	private static final Logger LOG = Logger.getLogger(AsignaturasImpl.class.getCanonicalName());

	@PersistenceContext(name = "grupoE")
	private EntityManager em;

	@Override
	public void importaAsignaturas(String file) throws SecretariaException {
		// TODO Auto-generated method stub

	}

	@Override
	public void modificarAsignatura(String codigo, String titulacion, Asignatura asig) throws SecretariaException {
		// TODO Auto-generated method stub

	}

	@Override
	public void borrarAsignatura(String codigo, String titulacion) throws SecretariaException {
		// TODO Auto-generated method stub

	}

	@Override
	public void definirGrupos(Grupos_asig ga) throws SecretariaException {
		// TODO Auto-generated method stub

	}

}
