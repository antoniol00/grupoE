package es.uma.informatica.sii.ejb;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import es.uma.informatica.sii.ejb.exceptions.SecretariaException;
import es.uma.informatica.sii.entities.Asigna_grupos;
import es.uma.informatica.sii.entities.Asigna_gruposPK;
import es.uma.informatica.sii.entities.Asignatura;
import es.uma.informatica.sii.entities.AsignaturaPK;
import es.uma.informatica.sii.entities.Clase;
import es.uma.informatica.sii.entities.ClasePK;
import es.uma.informatica.sii.entities.Grupo;
import es.uma.informatica.sii.entities.Matricula;
import es.uma.informatica.sii.entities.MatriculaPK;

@Stateless
public class AsignacionImpl implements GestionAsignacion {

	@PersistenceContext(name = "grupoE")
	private EntityManager em;

	@Override
	public void asignaGruposAlumnos() throws SecretariaException {
		// ALUMNOS DE NUEVO INGRESO
		// GIS
		TypedQuery<Matricula> query_matricula = em.createQuery(
				"select m from Matricula m where (m.nuevo_ingreso = true and m.expediente.numero LIKE '%1056%')",
				Matricula.class);
		List<Matricula> l = query_matricula.getResultList();
		TypedQuery<Grupo> query_grupo = em.createQuery(
				"select g from Grupo g where (g.curso=1 and g.visible=true and g.titulacion.codigo=1056)", Grupo.class);
		List<Grupo> g = query_grupo.getResultList();
		int index = 0;
		for (Matricula m : l) {
			for (int x = 101; x < 111; x++) {
				AsignaturaPK apk = new AsignaturaPK();
				apk.setCodigo(x);
				apk.setTitulacion(1056);
				Asignatura as = em.find(Asignatura.class, apk);
				Grupo grupo = g.get(index % g.size());
				Asigna_grupos ag1 = new Asigna_grupos();
				ag1.setAsignatura(as);
				ag1.setGrupo(grupo);
				ag1.setMatricula(m);
				em.persist(ag1);
			}
			index++;
		}
	}

	public boolean ColisionesHorario(int matricula) throws SecretariaException {
		boolean colision = false;
		List<Asigna_grupos> lista = listaAsignacionProvisional();
		Asigna_grupos comparador = new Asigna_grupos();
		HashSet<Grupo> cursos = new HashSet<>();
		for (int i = 0; i < lista.size(); i++) {
			comparador = lista.get(i);
			if (comparador.getMatricula().getNumero_archivo() == matricula) {
				cursos.add(comparador.getGrupo());
			}
		}
		List<Grupo> grupos = new ArrayList<Grupo>(cursos);
		for (int i = 0; i < grupos.size(); i++) {
			for (int k = i; k < grupos.size(); k++) {
				if (grupos.get(i).getTurno().equals(grupos.get(k).getTurno())) {
					colision = true;
				}
			}
		}
		return colision;
	}

	public void modificaGrupo(int codigo, int titulacion, String curso, int expediente, int id_grupo)
			throws SecretariaException {
		AsignaturaPK apk = new AsignaturaPK();
		apk.setCodigo(codigo);
		apk.setTitulacion(titulacion);
		Asignatura as = em.find(Asignatura.class, apk);

		MatriculaPK mpk = new MatriculaPK();
		mpk.setCurso(curso);
		mpk.setExpediente(expediente);
		Matricula m = em.find(Matricula.class, mpk);

		Grupo gr = em.find(Grupo.class, id_grupo);
		if (as == null || m == null || gr == null) {
			throw new SecretariaException("Se ha intentado modificar un grupo a un alumno de manera incorrecta");
		}
		Asigna_gruposPK agpk = new Asigna_gruposPK();
		agpk.setAsignatura(apk);
		agpk.setMatricula(mpk);
		Asigna_grupos ag = em.find(Asigna_grupos.class, agpk);
		if (ag == null) {
			throw new SecretariaException(
					"Error de modificacion de grupo. El alumno no tiene ninguna asignacion para la asignatura especificada");
		}
		ag.setGrupo(gr);
		em.merge(ag);

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

	// METODOS AUXILIARES
	@Override
	public List<Asigna_grupos> listaAsignacionProvisional() {
		TypedQuery<Asigna_grupos> query = em.createQuery("select a from Asigna_grupos a", Asigna_grupos.class);
		return query.getResultList();
	}

	@Override
	public Grupo obtenerGrupo(String id) throws SecretariaException {
		Grupo g = em.find(Grupo.class, id);
		if (g == null) {
			throw new SecretariaException("El grupo no existe");
		}
		return g;
	}

	@Override
	public Clase obtenerClase(String dia, String hora_inicio, String id_grupo) throws SecretariaException {
		ClasePK cpk = new ClasePK();
		cpk.setDia(dia);
		cpk.setHora_inicio(hora_inicio);
		cpk.setGrupo(id_grupo);
		Clase c = em.find(Clase.class, cpk);
		if (c == null) {
			throw new SecretariaException("La clase no existe");
		}
		return c;
	}

	@Override
	public List<Grupo> listaGrupos() {
		TypedQuery<Grupo> query = em.createQuery("select g from Grupo g", Grupo.class);
		return query.getResultList();
	}

	@Override
	public List<Clase> listaClases() {
		TypedQuery<Clase> query = em.createQuery("select c from Clase c", Clase.class);
		return query.getResultList();
	}

}
