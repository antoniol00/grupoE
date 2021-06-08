package es.uma.informatica.sii.ejb;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import es.uma.informatica.sii.ejb.exceptions.SecretariaException;
import es.uma.informatica.sii.ejb.exceptions.SecretariaIOException;
import es.uma.informatica.sii.entities.Alumno;
import es.uma.informatica.sii.entities.Expediente;
import es.uma.informatica.sii.entities.Titulacion;

@Stateless
public class AlumnosImpl implements GestionAlumnos {

	@PersistenceContext(name = "grupoE")
	private EntityManager em;

	private String DOCUMENTO = "DOCUMENTO", NOMBRE = "NOMBRE", APELLIDO1 = "APELLIDO1", APELLIDO2 = "APELLIDO2",
			EXPEDIENTE = "EXPEDIENTE", ARCHIVO = "ARCHIVO", EMAILINST = "EMAIL_INST", EMAILPER = "EMAIL_PER",
			FIJO = "FIJO", MOVIL = "MOVIL", DIR = "DIR", LOCAL = "LOCAL", PRO = "PRO", CP = "CP", FECHA = "FECHA",
			TURNO = "TURNO", GRUPOS = "GRUPOS", NOTA = "NOTA", CREDITOS = "CREDITOS", CREDITOSFB = "CREDITOS_FB",
			CREDITOSOB = "CREDITOS_OB", CREDITOSOP = "CREDITOS_OP", CREDITOSCF = "CREDITOSCF",
			CREDITOSPE = "CREDITOS_PE", CREDITOSTF = "CREDITOS_TF";

	// string de cabeceras para archivo alumnos.csv
	private String[] HEADERS = { DOCUMENTO, NOMBRE, APELLIDO1, APELLIDO2, EXPEDIENTE, ARCHIVO, EMAILINST, EMAILPER,
			FIJO, MOVIL, DIR, LOCAL, PRO, CP, FECHA, TURNO, GRUPOS, NOTA, CREDITOS, CREDITOSFB, CREDITOSOB, CREDITOSOP,
			CREDITOSCF, CREDITOSPE, CREDITOSTF };

	/**
	 * El fichero alumnos se encuentra en ./DATOS/alumnos.csv Para completar la
	 * tabla ALUMNOS esta sería la correspondencia entre columnas del csv y excel:
	 * CSV -> BBDD DOCUMENTO -> DNI NOMBRE+APELLIDO1+APELLIDO2 -> NOMBRE_COMPLETO
	 * EMAIL_INSTITUCIONAL -> EMAIL_INSTITUCIONAL EMAIL_PERSONAL -> EMAIL_PERSONAL
	 * TELEFONO -> TELEFONO_FIJO MOVIL -> TELEFONO_MOVIL DIRECCION_NOTIFICACION ->
	 * DIRECCION_NOTIFICACION LOCALIDAD_NOTIFICACION -> LOCALIDAD_NOTIFICACION
	 * PROVINCIA_NOTIFICACION -> PROVINCIA_NOTIFICACION CODIGO_POSTAL_NOTIFICACION
	 * -> CODIGO_POSTAL
	 * 
	 * @throws IOException         si hay un error en el acceso a alumnos.csv
	 * @throws SecretariaException si no encuentra en la bbdd alguna referencia a
	 *                             otra entidad
	 * 
	 */
	@Override
	public void importaAlumnos(String file) throws SecretariaIOException, SecretariaException {
		try {
			// RELLENAMOS TABLA ALUMNOS
			Reader in = new FileReader(file);
			Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader(HEADERS).withDelimiter(';').parse(in);
			int x = 0;
			for (CSVRecord r : records) {
				// eliminamos las primeras 3 lineas de basura + header
				if (x > 3) {
					String dni = r.get(DOCUMENTO);
					String nombre_completo = r.get(NOMBRE) + " " + r.get(APELLIDO1) + " "
							+ r.get(APELLIDO2);
					String email_inst = r.get(EMAILINST);
					String email_per = r.get(EMAILPER);
					String fijo = r.get(FIJO);
					String movil = r.get(MOVIL);
					String dir_not = r.get(DIR);
					String loc_not = r.get(LOCAL);
					String pro_not = r.get(PRO);
					String cp = r.get(CP);

					Alumno al = new Alumno();
					al.setDni(dni);
					al.setNombre_completo(nombre_completo);
					al.setEmail_institucional(email_inst);
					al.setEmail_personal(email_per);
					al.setTelefono_fijo(Integer.parseInt(fijo.replace(" ", "")));
					al.setTelefono_movil(Integer.parseInt(movil.replace(" ", "")));
					al.setDireccion_notificacion(dir_not);
					al.setLocalidad_notificacion(loc_not);
					al.setProvincia_notificacion(pro_not);
					al.setCodigo_postal(Integer.parseInt(cp));
					al.setExpedientes(new ArrayList<>());

					// introducimos en la base de datos cada alumno
					em.persist(al);

				} else {
					x++;
				}
			}

		} catch (IOException e) {
			throw new SecretariaIOException("Error IO de archivo " + file);
		}
	}

	@Override
	public void importaExpedientes(String file) throws SecretariaIOException, SecretariaException {
		try {
			// RELLENAMOS TABLA EXPEDIENTE
			Reader in2 = new FileReader(file);
			Iterable<CSVRecord> records2 = CSVFormat.DEFAULT.withHeader(HEADERS).withDelimiter(';').parse(in2);
			int y = 0;
			for (CSVRecord r : records2) {
				// eliminamos las primeras 3 lineas de basura + header
				if (y > 3) {
					String numero = r.get(EXPEDIENTE);
					String nota = r.get(NOTA);
					String creditos_fb = r.get(CREDITOSFB);
					String creditos_ob = r.get(CREDITOSOB);
					String creditos_pe = r.get(CREDITOSPE);
					String creditos_tf = r.get(CREDITOSTF);
					String dni = r.get(DOCUMENTO);

					Expediente ex = new Expediente();
					ex.setNumero(Integer.parseInt(numero));
					ex.setActivo(true);
					ex.setNota_media(Double.parseDouble(nota));
					ex.setCreditos_fb(Double.parseDouble(creditos_fb));
					ex.setCreditos_ob(Double.parseDouble(creditos_ob));
					ex.setCreditos_pe(Double.parseDouble(creditos_pe));
					ex.setCreditos_tf(Double.parseDouble(creditos_tf));
					Titulacion t = em.find(Titulacion.class, Integer.parseInt(numero.substring(0, 4)));
					if (t == null) {
						throw new SecretariaException("Se ha intentado asignar un codigo de titulacion que no existe");
					}
					ex.setTitulacion(t);
					Alumno al = em.find(Alumno.class, dni);
					if (al == null) {
						throw new SecretariaException("Se ha intentado asignar un alumno que no existe");
					}
					ex.setAlumno(al);

					// introducimos en la base de datos cada expediente
					em.persist(ex);

				} else {
					y++;
				}
			}

		} catch (IOException e) {
			throw new SecretariaIOException("Error IO de archivo");
		}
	}

	// METODOS AUXILIARES
	@Override
	public Alumno obtenerAlumno(String dni) throws SecretariaException {
		Alumno al = em.find(Alumno.class, dni);
		if (al == null) {
			throw new SecretariaException("El alumno no existe");
		}
		return al;
	}

	@Override
	public List<Alumno> obtenerListaAlumnos() {
		TypedQuery<Alumno> query = em.createQuery("select a from Alumno a", Alumno.class);
		return query.getResultList();
	}

	@Override
	public Expediente obtenerExpediente(int num) throws SecretariaException {
		Expediente ex = em.find(Expediente.class, num);
		if (ex == null) {
			throw new SecretariaException("El expediente no existe");
		}
		return ex;
	}

	@Override
	public List<Expediente> obtenerListaExpedientes() {
		TypedQuery<Expediente> query = em.createQuery("select a from Expediente a", Expediente.class);
		return query.getResultList();
	}

	@Override
	public void borraAlumno(Alumno a) {

		em.remove(em.contains(a) ? a : em.merge(a));
	}

}
