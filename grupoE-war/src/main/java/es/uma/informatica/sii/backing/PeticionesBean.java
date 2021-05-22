package es.uma.informatica.sii.backing;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

import es.uma.informatica.sii.ejb.AlumnosImpl;
import es.uma.informatica.sii.ejb.GestionAlumnos;
import es.uma.informatica.sii.ejb.GestionPeticiones;
import es.uma.informatica.sii.ejb.PeticionesImpl;
import es.uma.informatica.sii.ejb.exceptions.SecretariaException;
import es.uma.informatica.sii.ejb.exceptions.SecretariaIOException;
import es.uma.informatica.sii.entities.Peticion;

@Named
@RequestScoped
public class PeticionesBean {
	@EJB
	private GestionPeticiones pet = new PeticionesImpl();

	@EJB
	private GestionAlumnos al = new AlumnosImpl();

	private Peticion p;
	private String DNI;
	private Part part;
	private String fileName;

	public PeticionesBean() {
		p = new Peticion();
	}

	public List<Peticion> getPeticiones() {
		return pet.listaPeticiones();
	}

	public String crearPeticion() throws SecretariaException, SecretariaIOException, IOException {
		p.setDate(new Date());
		p.setAlumno(al.obtenerAlumno(DNI));
		pet.creaIncidencia(p);
		p = new Peticion();
		return null;
	}

	public String getAsignarDNI() {
		return DNI;
	}

	public void setAsignarDNI(String dni) {
		this.DNI = dni;
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

	public Peticion getPeticion() {
		return p;
	}

	public String upload() throws IOException, SecretariaIOException, SecretariaException {

		fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
		part.write(fileName);
		al.importaAlumnos(System.getProperty("jboss.server.temp.dir")
				+ "/grupoE-ear-0.0.1-SNAPSHOT.ear.grupoE-war-0.0.1-SNAPSHOT.war/" + fileName);
		return null;
	}

}
