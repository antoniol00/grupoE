package es.uma.informatica.sii.ejb;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import es.uma.informatica.sii.ejb.exceptions.SecretariaException;
import es.uma.informatica.sii.entities.Expediente;
import es.uma.informatica.sii.entities.Matricula;
import es.uma.informatica.sii.entities.MatriculaPK;

@Stateless
public class MatriculasImpl implements GestionMatriculas {

	@PersistenceContext(name = "grupoE")
	private EntityManager em;

	@Override
	public void importaMatriculas(String file) throws SecretariaException, IOException, ParseException {
		// string de cabeceras para archivo alumnos.csv
		String[] HEADERS = { "DOCUMENTO", "NOMBRE", "APELLIDO1", "APELLIDO2", "EXPEDIENTE", "ARCHIVO", "EMAIL_INST",
				"EMAIL_PER", "FIJO", "MOVIL", "DIR", "LOCAL", "PRO", "CP", "FECHA", "TURNO", "GRUPOS", "NOTA",
				"CREDITOS", "CREDITOS_FB", "CREDITOS_OB", "CREDITOS_OP", "CREDITOS_CF", "CREDITOS_PE", "CREDITOS_TF" };

		// RELLENAMOS TABLA MATRICULA
		Reader in = new FileReader("./DATOS/alumnos.csv");
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader(HEADERS).withDelimiter(';').parse(in);
		int x = 0;
		for (CSVRecord record : records) {
			// eliminamos las primeras 3 lineas de basura + header
			if (x > 3) {
				String numero_archivo = record.get("ARCHIVO");
				String turno = record.get("TURNO");
				String fecha_matricula = record.get("FECHA");
				String grupos = record.get("GRUPOS");
				String expediente = record.get("EXPEDIENTE");

				Matricula m = new Matricula();
				m.setCurso("20/21");
				m.setNumero_archivo(Integer.parseInt(numero_archivo));
				m.setActiva(true);
				m.setTurno(turno);
				if (expediente.substring(0, 3).equalsIgnoreCase("1073")) {
					m.setTipo_estudio("DOBLEGRADO");
				} else {
					m.setTipo_estudio("GRADO");
				}
				if (record.get("CREDITOS").equals("0")
						&& grupos.equals("101-,102-,103-,104-,105-,106-,107-,108-,109-,110-")) {
					m.setNuevo_ingreso(true);
				} else {
					m.setNuevo_ingreso(false);
				}
				m.setFecha_matricula(new SimpleDateFormat("dd/MM/yyyy HH/mm").parse(fecha_matricula));
				m.setListado_asignaturas(grupos);
				Expediente exp = em.find(Expediente.class, Integer.parseInt(expediente));
				if (exp == null) {
					throw new SecretariaException("Se ha intentado crear una matricula con un expediente inexistente");
				}
				m.setExpediente(exp);

				// introducimos en la base de datos cada matricula
				em.persist(m);

			} else {
				x++;
			}
		}
	}

	@Override
	public List<Matricula> listaMatriculas(String filter) throws SecretariaException {
		String sentencia = "";
		switch (filter) {
		case "DATE":
			sentencia = "select m from Matricula m order by m.fecha_matricula";
			break;
		case "EXP":
			sentencia = "select m from Matricula m order by m.expediente";
			break;
		case "DES":
			sentencia = "select m from Matricula m order by m.numero_archivo DESC";
			break;
		case "ASC":
			sentencia = "select m from Matricula m order by m.numero_archivo ASC";
			break;
		default:
			throw new SecretariaException("Filtro incorrecto. Posibles filtros: DATE, EXP, DES o ASC");
		}
		TypedQuery<Matricula> query = em.createQuery(sentencia, Matricula.class);
		return query.getResultList();

	}

	// NO NECESITA TEST
	@Override
	public Matricula obtenerMatricula(String curso, Integer expediente) throws SecretariaException {
		MatriculaPK mpk = new MatriculaPK();
		mpk.setCurso(curso);
		mpk.setExpediente(expediente);
		Matricula m = em.find(Matricula.class, mpk);
		if (m == null) {
			throw new SecretariaException("La matricula no existe");
		}
		return m;
	}

}
