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

import es.uma.informatica.sii.entities.Titulacion;

public class BaseDatos {
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

		em.getTransaction().commit();

		em.close();
		emf.close();
	}
}
