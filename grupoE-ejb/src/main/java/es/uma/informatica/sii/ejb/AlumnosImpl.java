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
			// string de cabeceras para archivo alumnos.csv
			String[] HEADERS = { "DOCUMENTO", "NOMBRE", "APELLIDO1", "APELLIDO2", "EXPEDIENTE", "ARCHIVO", "EMAIL_INST",
					"EMAIL_PER", "FIJO", "MOVIL", "DIR", "LOCAL", "PRO", "CP", "FECHA", "TURNO", "GRUPOS", "NOTA",
					"CREDITOS", "CREDITOS_FB", "CREDITOS_OB", "CREDITOS_OP", "CREDITOS_CF", "CREDITOS_PE",
					"CREDITOS_TF" };

			// RELLENAMOS TABLA ALUMNOS
			Reader in = new FileReader(file);
			Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader(HEADERS).withDelimiter(';').parse(in);
			int x = 0;
			for (CSVRecord record : records) {
				// eliminamos las primeras 3 lineas de basura + header
				if (x > 3) {
					String dni = record.get("DOCUMENTO");
					String nombre_completo = record.get("NOMBRE") + " " + record.get("APELLIDO1") + " "
							+ record.get("APELLIDO2");
					String email_inst = record.get("EMAIL_INST");
					String email_per = record.get("EMAIL_PER");
					String fijo = record.get("FIJO");
					String movil = record.get("MOVIL");
					String dir_not = record.get("DIR");
					String loc_not = record.get("LOCAL");
					String pro_not = record.get("PRO");
					String cp = record.get("CP");

					Alumno al = new Alumno();
					al.setDni(dni);
					al.setNombre_completo(nombre_completo);
					al.setEmail_institucional(email_inst);
					al.setEmail_personal(email_per);
					al.setTelefono_fijo(Integer.parseInt(fijo.replaceAll(" ", "")));
					al.setTelefono_movil(Integer.parseInt(movil.replaceAll(" ", "")));
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
			// string de cabeceras para archivo alumnos.csv
			String[] HEADERS = { "DOCUMENTO", "NOMBRE", "APELLIDO1", "APELLIDO2", "EXPEDIENTE", "ARCHIVO", "EMAIL_INST",
					"EMAIL_PER", "FIJO", "MOVIL", "DIR", "LOCAL", "PRO", "CP", "FECHA", "TURNO", "GRUPOS", "NOTA",
					"CREDITOS", "CREDITOS_FB", "CREDITOS_OB", "CREDITOS_OP", "CREDITOS_CF", "CREDITOS_PE",
					"CREDITOS_TF" };

			// RELLENAMOS TABLA EXPEDIENTE
			Reader in2 = new FileReader(file);
			Iterable<CSVRecord> records2 = CSVFormat.DEFAULT.withHeader(HEADERS).withDelimiter(';').parse(in2);
			int y = 0;
			for (CSVRecord record : records2) {
				// eliminamos las primeras 3 lineas de basura + header
				if (y > 3) {
					String numero = record.get("EXPEDIENTE");
					String nota = record.get("NOTA");
					String creditos_fb = record.get("CREDITOS_FB");
					String creditos_ob = record.get("CREDITOS_OB");
					String creditos_pe = record.get("CREDITOS_PE");
					String creditos_tf = record.get("CREDITOS_TF");
					String dni = record.get("DOCUMENTO");

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
	public void creaAlumno(Alumno a) {
		em.persist(a);
	}

}
