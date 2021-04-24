package es.uma.informatica.sii.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import es.uma.informatica.sii.entities.Grupo;
import es.uma.informatica.sii.entities.Titulacion;

public class BaseDatos {
	@SuppressWarnings({ "resource", "deprecation" })
	public static void inicializaBaseDatos(String nombreUnidadPersistencia) throws IOException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(nombreUnidadPersistencia);
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		// leemos archivo titulaciones.xlsx para importarlas al iniciar base de datos
		FileInputStream filex = new FileInputStream(new File("./DATOS/titulaciones.xlsx"));
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
				String codigo = values.get(0);
				String nombre = values.get(1);
				String creditos = values.get(2);

				Titulacion t = new Titulacion();
				t.setCodigo(Integer.parseInt(codigo.substring(0, 4)));
				t.setNombre(nombre);
				t.setCreditos(Integer.parseInt(creditos.substring(0, 3)));

				// inserto cada titulacion
				em.persist(t);
			}
		}

		int[] index = { 1041, 1042, 1043, 1056, 1073 };
		int x = 0;
		for (int i : index) {
			Grupo gs1 = new Grupo();
			gs1.setId("g" + x);
			x++;
			gs1.setCurso(1);
			gs1.setLetra("A");
			gs1.setVisible(true);
			gs1.setIngles(false);
			gs1.setTurno("M");
			gs1.setTitulacion(em.find(Titulacion.class, i));
			em.persist(gs1);

			Grupo gs2 = new Grupo();
			gs2.setId("g" + x);
			x++;
			gs2.setCurso(1);
			gs2.setLetra("B");
			gs2.setVisible(true);
			gs2.setIngles(false);
			gs2.setTurno("M");
			gs2.setTitulacion(em.find(Titulacion.class, i));
			em.persist(gs2);
		}

		em.getTransaction().commit();

		em.close();
		emf.close();
	}
}
