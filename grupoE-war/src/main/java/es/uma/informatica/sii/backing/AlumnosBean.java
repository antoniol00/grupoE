package es.uma.informatica.sii.backing;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

import es.uma.informatica.sii.ejb.AlumnosImpl;
import es.uma.informatica.sii.ejb.GestionAlumnos;
import es.uma.informatica.sii.ejb.GestionMatriculas;
import es.uma.informatica.sii.ejb.MatriculasImpl;
import es.uma.informatica.sii.entities.Alumno;

@Named
@RequestScoped
@SuppressWarnings("unused")
public class AlumnosBean {
	@EJB
	private GestionAlumnos al = new AlumnosImpl();

	@EJB
	private GestionMatriculas gm = new MatriculasImpl();

	private Part part;
	private String fileName;
	private String containerID;
	private String prevID;
	private int id;
	private String mensaje;

	public AlumnosBean() {
		id = 0;
	}

	public String upload() {
		String ruta = "";
		try {
			fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
			part.write(fileName);
			ruta = System.getProperty("jboss.server.temp.dir") + "/grupoE-ear.ear.grupoE-war-0.0.1-SNAPSHOT.war/"
					+ fileName;

			al.importaAlumnos(ruta);
			al.importaExpedientes(ruta);
			gm.importaMatriculas(ruta);

			File file = new File(ruta);
			file.delete();

			mensaje = "Importación correcta";

			return null;

		} catch (Exception e) {
			mensaje = "Error de importación. Sin cambios";
			if (!ruta.isEmpty()) {
				File file = new File(ruta);
				file.delete();
			}
			return null;
		}
	}

	public String vaciarDatos() {
		for (Alumno a : al.obtenerListaAlumnos()) {
			al.borraAlumno(a);
		}
		mensaje = "Borrado correcto";
		return null;
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

}
