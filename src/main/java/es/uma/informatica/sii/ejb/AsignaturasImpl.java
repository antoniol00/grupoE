package es.uma.informatica.sii.ejb;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import es.uma.informatica.sii.ejb.exceptions.SecretariaException;
import es.uma.informatica.sii.entities.Asignatura;
import es.uma.informatica.sii.entities.AsignaturaPK;
import es.uma.informatica.sii.entities.Grupo;
import es.uma.informatica.sii.entities.Grupos_asig;
import es.uma.informatica.sii.entities.Optativa;
import es.uma.informatica.sii.entities.Titulacion;

@Stateless
public class AsignaturasImpl implements GestionAsignaturas {

	@PersistenceContext(name = "grupoE")
	private EntityManager em;

	@SuppressWarnings("deprecation")
	@Override
	public void importaAsignaturas(String file) throws SecretariaException, IOException {
		FileInputStream filex = new FileInputStream(new File(file));
		XSSFWorkbook workbook = new XSSFWorkbook(filex);
		XSSFSheet sheet;
		Iterator<Row> rowIterator;
		// IMPORTAMOS DE GII, GISw, GICom y GII+GM (x)
		int[] index = { 2, 3, 4, 6 };
		for (int x : index) {
			sheet = workbook.getSheetAt(x);
			rowIterator = sheet.iterator();
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
					String titulacion = values.get(0);
					String ofertada = values.get(1);
					String codigo = values.get(2);
					String referencia = values.get(3);
					String nombre = values.get(4);
					String curso = values.get(5);
					String teoria = values.get(6);
					String practica = values.get(7);
					String cuatrimestre = values.get(9);
					String ingles = values.get(11);

					Asignatura as = new Asignatura();
					as.setReferencia(Integer.parseInt(referencia.substring(0, 5)));
					as.setCodigo(Integer.parseInt(codigo.substring(0, 3)));
					as.setCreditos_teoricos(Double.parseDouble(teoria));
					as.setCreditos_practicos(Double.parseDouble(practica));
					if (ofertada.equalsIgnoreCase("N"))
						as.setOfertada(false);
					else
						as.setOfertada(true);
					as.setNombre(nombre);
					as.setCurso(Integer.parseInt(curso.substring(0, 1)));
					as.setCuatrimestre(Integer.parseInt(cuatrimestre.substring(0, 1)));
					as.setIdiomas(ingles);
					Titulacion t = em.find(Titulacion.class, Integer.parseInt(titulacion.substring(0, 4)));
					if (t == null) {
						throw new SecretariaException(
								"Se ha intentado crear una asignatura para una titulacion que no existe");
					}
					as.setTitulacion(t);
					em.persist(as);

				}
			}
		}

		// IMPORTAMOS DE GIS

		sheet = workbook.getSheetAt(5);
		rowIterator = sheet.iterator();
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
				String titulacion = values.get(0);
				String ofertada = values.get(1);
				String codigo = values.get(2);
				String referencia = values.get(3);
				String nombre = values.get(4);
				String curso = values.get(5);
				String teoria = values.get(6);
				String practica = values.get(7);
				String cuatrimestre = values.get(9);

				Asignatura as = new Asignatura();
				as.setReferencia(Integer.parseInt(referencia.substring(0, 5)));
				as.setCodigo(Integer.parseInt(codigo.substring(0, 3)));
				as.setCreditos_teoricos(Double.parseDouble(teoria));
				as.setCreditos_practicos(Double.parseDouble(practica));
				if (ofertada.equalsIgnoreCase("N"))
					as.setOfertada(false);
				else
					as.setOfertada(true);
				as.setNombre(nombre);
				as.setCurso(Integer.parseInt(curso.substring(0, 1)));
				as.setCuatrimestre(Integer.parseInt(cuatrimestre.substring(0, 1)));
				Titulacion t = em.find(Titulacion.class, Integer.parseInt(titulacion.substring(0, 4)));
				if (t == null) {
					throw new SecretariaException(
							"Se ha intentado crear una asignatura para una titulacion que no existe");
				}
				as.setTitulacion(t);
				em.persist(as);
			}
		}

		workbook.close();
		filex.close();

	}

	@SuppressWarnings("deprecation")
	@Override
	public void importaOptativas(String file) throws SecretariaException, IOException {
		FileInputStream filex = new FileInputStream(new File(file));
		XSSFWorkbook workbook = new XSSFWorkbook(filex);
		XSSFSheet sheet;
		Iterator<Row> rowIterator;
		// IMPORTAMOS OPTATIVAS DE INFORMATICA Y DEL RESTO
		int[] index = { 0, 1 };
		for (int x : index) {
			sheet = workbook.getSheetAt(x);
			rowIterator = sheet.iterator();
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
					String titulacion = values.get(3);
					String referencia = values.get(0);
					String mencion = values.get(2);
					String plazas = values.get(1);
					
					TypedQuery<Asignatura> query = em.createQuery(
							"select a from Asignatura a where a.titulacion.codigo=:titulacion and a.referencia=:referencia",
							Asignatura.class);
					query.setParameter("titulacion", Integer.parseInt(titulacion.substring(0, 4)));
					query.setParameter("referencia", Integer.parseInt(referencia.substring(0, 5)));
					Asignatura a = query.getResultList().get(0);
					AsignaturaPK apk = new AsignaturaPK();
					apk.setCodigo(a.getCodigo());
					apk.setTitulacion(a.getTitulacion().getCodigo());
					Asignatura as = em.find(Asignatura.class, apk);
					
					Optativa o = new Optativa();
					if (plazas.equals("999.0")) {
						o.setPlazas(999);
					} else {
						o.setPlazas(35);
					}
					o.setMencion(mencion);
					o.setCodigo(as.getCodigo());
					o.setCreditos_practicos(as.getCreditos_practicos());
					o.setCreditos_teoricos(as.getCreditos_teoricos());
					o.setOfertada(as.getOfertada());
					o.setNombre(as.getNombre());
					o.setTitulacion(as.getTitulacion());
					em.persist(o);
				}
			}
		}
		workbook.close();
		filex.close();

	}

	@Override
	public void modificarAsignatura(int codigo, int titulacion, Asignatura asig) throws SecretariaException {
		AsignaturaPK apk = new AsignaturaPK();
		apk.setCodigo(codigo);
		apk.setTitulacion(titulacion);
		Asignatura as = em.find(Asignatura.class, apk);
		if (as == null) {
			throw new SecretariaException("Se ha intentado modificar una asignatura que no existe");
		}
		em.merge(asig);

	}

	@Override
	public void borrarAsignatura(int codigo, int titulacion) throws SecretariaException {
		AsignaturaPK apk = new AsignaturaPK();
		apk.setCodigo(codigo);
		apk.setTitulacion(titulacion);
		Asignatura as = em.find(Asignatura.class, apk);
		if (as == null) {
			throw new SecretariaException("Se ha intentado borrar una asignatura que no existe");
		}
		em.remove(as);
	}

	@Override
	public void definirGrupos(String codigo, String titulacion, String id, String curso) throws SecretariaException {
		AsignaturaPK apk = new AsignaturaPK();
		apk.setCodigo(Integer.parseInt(codigo));
		apk.setTitulacion(Integer.parseInt(titulacion));
		Asignatura as = em.find(Asignatura.class, apk);
		if (as == null) {
			throw new SecretariaException("Se ha intentado vincular una asignatura que no existe");
		}
		Grupo g = em.find(Grupo.class, id);
		if (g == null) {
			throw new SecretariaException("Se ha intentado vincular un grupo que no existe");
		}
		Grupos_asig ga = new Grupos_asig();
		ga.setAsignatura_codigo(as);
		ga.setCurso(curso);
		ga.setGrupo_id(g);

		em.persist(ga);

	}

	// NO NECESITAN TEST

	@Override
	public Asignatura obtenerAsignatura(int codigo, int titulacion) throws SecretariaException {
		AsignaturaPK apk = new AsignaturaPK();
		apk.setCodigo(codigo);
		apk.setTitulacion(titulacion);
		Asignatura as = em.find(Asignatura.class, apk);
		if (as == null) {
			throw new SecretariaException("La asignatura no existe");
		}
		return as;
	}

	@Override
	public List<Asignatura> listarAsignaturas() throws SecretariaException {
		TypedQuery<Asignatura> query = em.createQuery("select a from Asignatura a", Asignatura.class);
		return query.getResultList();
	}

}
