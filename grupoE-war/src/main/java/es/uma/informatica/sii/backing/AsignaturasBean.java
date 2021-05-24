package es.uma.informatica.sii.backing;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

import es.uma.informatica.sii.ejb.AsignaturasImpl;
import es.uma.informatica.sii.ejb.GestionAsignaturas;
import es.uma.informatica.sii.ejb.exceptions.SecretariaException;
import es.uma.informatica.sii.ejb.exceptions.SecretariaIOException;
import es.uma.informatica.sii.entities.Asignatura;

@Named
@RequestScoped
@SuppressWarnings("unused")
public class AsignaturasBean {
	@EJB
	private GestionAsignaturas ga = new AsignaturasImpl();

	private Asignatura asig;

	private Part part;
	private String fileName;
	private String mensaje;

	private String containerID;
	private String prevID;
	private int id;

	public AsignaturasBean() {
		asig = new Asignatura();
		mensaje = "";
		id = 0;
	}

	public List<Asignatura> getAsignaturas() {
		return ga.listarAsignaturas();
	}

	public String eliminarAsignatura(Asignatura asig) throws SecretariaException {
		ga.borrarAsignatura(asig.getCodigo(), asig.getTitulacion().getCodigo());
		return null;
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

	public Asignatura getAsignatura() {
		return asig;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
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

		ga.importaAsignaturas(ruta);

		File file = new File(ruta);
		file.delete();

		mensaje = "Importación correcta";

		return null;
	}

	public String vaciarDatos() throws SecretariaException {
		for (Asignatura as : ga.listarAsignaturas()) {
			ga.borrarAsignatura(as.getCodigo(), as.getTitulacion().getCodigo());
		}
		mensaje = "Borrado correcto";
		return null;
	}

	public Asignatura getAsig() {
		return asig;
	}

	public void setAsig(Asignatura asig) {
		this.asig = asig;
	}
}
