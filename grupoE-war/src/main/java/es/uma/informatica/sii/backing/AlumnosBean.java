package es.uma.informatica.sii.backing;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

import es.uma.informatica.sii.ejb.AlumnosImpl;
import es.uma.informatica.sii.ejb.GestionAlumnos;
import es.uma.informatica.sii.ejb.exceptions.SecretariaException;
import es.uma.informatica.sii.ejb.exceptions.SecretariaIOException;
import es.uma.informatica.sii.entities.Alumno;

@Named
@RequestScoped
@SuppressWarnings("unused")
public class AlumnosBean {
	@EJB
	private GestionAlumnos al = new AlumnosImpl();

	private Alumno alum;

	private Part part;
	private String fileName;
	private String containerID;
	private String prevID;
	private int id;
	private String mensaje;

	public AlumnosBean() {
		alum = new Alumno();
		id = 0;
	}

	public List<Alumno> getAlumnos() {
		return al.obtenerListaAlumnos();
	}

	public Part getPart() {
		return part;
	}

	public void setPart(Part part) {
		this.part = part;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Alumno getAlumno() {
		return alum;
	}

	public String getContainerID() {
		id++;
		return "#containerID" + id;
	}

	public String getPrevID() {
		return "containerID" + id;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String upload() throws IOException, SecretariaIOException, SecretariaException {

		fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
		part.write(fileName);
		String ruta = System.getProperty("jboss.server.temp.dir")
				+ "/grupoE-ear-0.0.1-SNAPSHOT.ear.grupoE-war-0.0.1-SNAPSHOT.war/" + fileName;
		al.importaAlumnos(ruta);
		al.importaExpedientes(ruta);

		File file = new File(ruta);
		file.delete();

		mensaje = "Imporación correcta";

		return null;
	}

	public String vaciarDatos() {
		for (Alumno a : al.obtenerListaAlumnos()) {
			al.borraAlumno(a);
		}
		mensaje = "Borrado correcto";
		return null;
	}
}
