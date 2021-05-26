package es.uma.informatica.sii.backing;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

import es.uma.informatica.sii.ejb.MatriculasImpl;
import es.uma.informatica.sii.ejb.AlumnosImpl;
import es.uma.informatica.sii.ejb.GestionAlumnos;
import es.uma.informatica.sii.ejb.GestionMatriculas;
import es.uma.informatica.sii.ejb.exceptions.SecretariaException;
import es.uma.informatica.sii.ejb.exceptions.SecretariaIOException;
import es.uma.informatica.sii.entities.Alumno;
import es.uma.informatica.sii.entities.Matricula;

@Named
@RequestScoped
@SuppressWarnings("unused")
public class MatriculasBean {
	@EJB
	private GestionMatriculas mat = new MatriculasImpl();
	
	private Matricula matr;
	
	private Part part;
	private String fileName;
	private String containerID;
	private String prevID;
	private int id;
	private String mensaje;
	
	public MatriculasBean() {
		matr = new Matricula();
		id = 0;
	}
	
	public List<Matricula> getMatriculas() throws SecretariaException {
		return mat.listaMatriculas(containerID);
		
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
	
	public Matricula getMatricula() {
		return matr;
	}
	
	public String getContainerID() {
		id++;
		return "#containerID" + id;
	}

	public String getPrevID() {
		return "containerID" + id;
	}
	
	public String upload() throws IOException, SecretariaIOException, SecretariaException {

		fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
		part.write(fileName);
		String ruta = System.getProperty("jboss.server.temp.dir")
				+ "/grupoE-ear-0.0.1-SNAPSHOT.ear.grupoE-war-0.0.1-SNAPSHOT.war/" + fileName;
		mat.importaMatriculas(ruta);

		File file = new File(ruta);
		file.delete();

		mensaje = "Imporacion correcta";

		return null;
	}
	
}
