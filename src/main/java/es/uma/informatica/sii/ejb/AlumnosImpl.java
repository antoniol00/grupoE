package es.uma.informatica.sii.ejb;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.uma.informatica.sii.ejb.exceptions.SecretariaException;
import es.uma.informatica.sii.entities.Alumno;

@Stateless
public class AlumnosImpl implements GestionAlumnos {

	private static final Logger LOG = Logger.getLogger(AlumnosImpl.class.getCanonicalName());

	@PersistenceContext(name = "grupoE")
	private EntityManager em;

	@Override
	public void importaAlumnos(String file) throws SecretariaException {
		// TODO Auto-generated method stub

	}

	@Override
	public void modificaAlumno(String dni_alumno, Alumno al) throws SecretariaException {
		// TODO Auto-generated method stub

	}

}
