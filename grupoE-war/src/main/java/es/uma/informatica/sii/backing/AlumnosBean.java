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
import es.uma.informatica.sii.ejb.exceptions.SecretariaException;
import es.uma.informatica.sii.ejb.exceptions.SecretariaIOException;
import es.uma.informatica.sii.entities.Alumno;

@Named
@RequestScoped
public class AlumnosBean {
	@EJB
	private GestionAlumnos al = new AlumnosImpl();

	private Alumno alum;
	private String DNI;
	private String nombre;
	private String email_inst;
	private String email_pers;
	private Integer telef_fijo;
	private Integer telef_mov;
	private String direc;
	private String localidad;
	private String provincia;
	private Integer codPost;

	private Part part;
	private String fileName;

	public AlumnosBean() {
		alum = new Alumno();
	}

	public List<Alumno> getAlumnos() {
		return al.obtenerListaAlumnos();
	}

	/*public String crearPeticion() throws SecretariaException, SecretariaIOException, IOException {
		p.setDate(new Date());
		p.setAlumno(al.obtenerAlumno(DNI));
		pet.creaIncidencia(p);
		p = new Peticion();
		return null;
	}
	 */
	public String getAsignarDNI() {
		return DNI;
	}

	public void setAsignarDNI(String dni) {
		this.DNI = dni;
	}

	
	public String getAsignarNombre() {
		return nombre;
	}

	public void setAsignarNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	public String getAsignarEmailInst() {
		return email_inst;
	}

	public void setAsignarEmailInst(String email_inst) {
		this.email_inst = email_inst;
	}
	
	
	public String getAsignarEmailPers() {
		return email_pers;
	}

	public void setAsignarEmailPers(String email_pers) {
		this.email_pers = email_pers;
	}
	
	
	public Integer getAsignarTelFij() {
		return telef_fijo;
	}

	public void setAsignarTelFij(Integer telef_fijo) {
		this.telef_fijo = telef_fijo;
	}
	
	
	public Integer getAsignarTelMov() {
		return telef_mov;
	}

	public void setAsignarTelMov(Integer telef_mov) {
		this.telef_mov = telef_mov;
	}
	
	
	public String getAsignarDireccion() {
		return direc;
	}

	public void setAsignarDireccion(String direc) {
		this.direc = direc;
	}
	
	
	public String getAsignarLocal() {
		return localidad;
	}

	public void setAsignarLocal(String localidad) {
		this.localidad = localidad;
	}
	
	
	public String getAsignarProv() {
		return provincia;
	}

	public void setAsignarProv(String provincia) {
		this.provincia = provincia;
	}
	
	
	public Integer getAsignarCP() {
		return codPost;
	}

	public void setAsignarCP(Integer codPost) {
		this.codPost = codPost;
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

	public String upload() throws IOException, SecretariaIOException, SecretariaException {

		fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
		part.write(fileName);
		al.importaAlumnos(System.getProperty("jboss.server.temp.dir")
				+ "/grupoE-ear-0.0.1-SNAPSHOT.ear.grupoE-war-0.0.1-SNAPSHOT.war/" + fileName);
		return null;
	}
}
