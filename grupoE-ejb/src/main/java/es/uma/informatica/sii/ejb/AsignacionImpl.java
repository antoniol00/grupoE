package es.uma.informatica.sii.ejb;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import es.uma.informatica.sii.ejb.exceptions.SecretariaException;
import es.uma.informatica.sii.ejb.exceptions.SecretariaIOException;
import es.uma.informatica.sii.entities.Asigna_grupos;
import es.uma.informatica.sii.entities.Asigna_gruposPK;
import es.uma.informatica.sii.entities.Asignatura;
import es.uma.informatica.sii.entities.AsignaturaPK;
import es.uma.informatica.sii.entities.Clase;
import es.uma.informatica.sii.entities.ClasePK;
import es.uma.informatica.sii.entities.Grupo;
import es.uma.informatica.sii.entities.Matricula;
import es.uma.informatica.sii.entities.MatriculaPK;
import es.uma.informatica.sii.entities.Titulacion;

@Stateless
public class AsignacionImpl implements GestionAsignacion {

	@PersistenceContext(name = "grupoE")
	private EntityManager em;

	@Override
	public void asignaGruposAlumnos() throws SecretariaException {
		// ALUMNOS DE NUEVO INGRESO
		int[] index = { 1041, 1042, 1043, 1056, 1073 };
		for (int x : index) {
			TypedQuery<Matricula> query_matricula = em.createQuery(
					"select m from Matricula m where (m.nuevo_ingreso = true and m.expediente.numero LIKE '" + x
							+ "%')",
					Matricula.class);
			List<Matricula> l = query_matricula.getResultList();
			TypedQuery<Grupo> query_grupo = em.createQuery(
					"select g from Grupo g where (g.curso=1 and g.visible=true and g.titulacion.codigo = " + x + ")",
					Grupo.class);
			List<Grupo> g = query_grupo.getResultList();
			int idx = 0;
			int max_value = (x == 1073 ? 111 : 110);
			for (Matricula m : l) {
				for (int y = 101; y <= max_value; y++) {
					AsignaturaPK apk = new AsignaturaPK();
					apk.setCodigo(y);
					apk.setTitulacion(x);
					Asignatura as = em.find(Asignatura.class, apk);
					Grupo grupo = g.get(idx % g.size());
					Asigna_grupos ag1 = new Asigna_grupos();
					ag1.setAsignatura(as);
					ag1.setGrupo(grupo);
					ag1.setMatricula(m);
					em.persist(ag1);
				}
				idx++;
			}
		}
	}

	public boolean ColisionesHorario(int matricula) throws SecretariaException {
		List<Asigna_grupos> lista = listaAsignacionProvisional().stream()
				.filter(a -> a.getMatricula().getNumero_archivo() == matricula).collect(Collectors.toList());

		if (!lista.isEmpty()) {
			HashSet<String> hss = new HashSet<String>();
			for (Asigna_grupos ag : lista) {
				hss.add(ag.getGrupo().getTurno());
			}
			return hss.size() == 2;
		} else {
			throw new SecretariaException("No tiene grupos asignados");
		}

	}

	public void modificaGrupo(int codigo, int titulacion, String curso, int expediente, String id_grupo)
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

	@Override
	public void borraAsignacion(Asigna_grupos ag) {
		em.remove(em.merge(ag));
	}

	@SuppressWarnings({ "resource", "deprecation" })
	@Override
	public void importaGrupos(String file) throws SecretariaException, SecretariaIOException {
		try {
			FileInputStream filex = new FileInputStream(new File(file));
			XSSFWorkbook workbook = new XSSFWorkbook(filex);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			rowIterator.next();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				ArrayList<String> values = new ArrayList<>();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						values.add(Double.toString(cell.getNumericCellValue()));
						break;
					case Cell.CELL_TYPE_STRING:
						values.add(cell.getStringCellValue());
						break;
					default:
						values.add("");
					}
				}
				if (!values.get(0).equals("")) {
					String id = values.get(0);
					String curso = values.get(1);
					String letra = values.get(2);
					String turno = values.get(3);
					String ingles = values.get(4);
					String visible = values.get(5);
					String titulacion = values.get(7);

					Grupo g = new Grupo();
					g.setId(id);
					g.setCurso(Integer.parseInt(curso.substring(0, 1)));
					g.setLetra(letra);
					g.setTurno(turno);
					g.setIngles(ingles.equals("S") ? true : false);
					g.setVisible(visible.equals("S") ? true : false);
					Titulacion t = em.find(Titulacion.class, Integer.parseInt(titulacion.substring(0, 4)));
					if (t == null) {
						throw new SecretariaException("Titulacion no existente " + titulacion);
					}
					g.setTitulacion(t);

					// inserto cada grupo
					em.persist(g);
				}
			}
		} catch (IOException e) {
			throw new SecretariaIOException("Error en el archivo");
		}
	}

	// METODOS AUXILIARES
	@Override
	public List<Asigna_grupos> listaAsignacionProvisional() {
		TypedQuery<Asigna_grupos> query = em.createQuery(
				"select a from Asigna_grupos a order by a.matricula.expediente.numero", Asigna_grupos.class);
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

	@Override
	public Asigna_grupos obtieneAsignacion(int codigo, int titulacion, String curso, int expediente)
			throws SecretariaException {
		AsignaturaPK apk = new AsignaturaPK();
		apk.setCodigo(codigo);
		apk.setTitulacion(titulacion);
		Asignatura as = em.find(Asignatura.class, apk);

		MatriculaPK mpk = new MatriculaPK();
		mpk.setCurso(curso);
		mpk.setExpediente(expediente);
		Matricula m = em.find(Matricula.class, mpk);

		if (as == null || m == null) {
			throw new SecretariaException("Asignatura o matricula no encontrada");
		}

		Asigna_gruposPK agpk = new Asigna_gruposPK();
		agpk.setAsignatura(apk);
		agpk.setMatricula(mpk);
		Asigna_grupos ag = em.find(Asigna_grupos.class, agpk);

		if (ag == null) {
			throw new SecretariaException("Asignacion no realizada");
		}
		return ag;
	}

}
