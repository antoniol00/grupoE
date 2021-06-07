package es.uma.informatica.sii.backing;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

import es.uma.informatica.sii.ejb.AsignacionImpl;
import es.uma.informatica.sii.ejb.AsignaturasImpl;
import es.uma.informatica.sii.ejb.GestionAsignacion;
import es.uma.informatica.sii.ejb.GestionAsignaturas;
import es.uma.informatica.sii.ejb.exceptions.SecretariaException;
import es.uma.informatica.sii.ejb.exceptions.SecretariaIOException;
import es.uma.informatica.sii.entities.Asignatura;
import es.uma.informatica.sii.entities.Grupo;
import es.uma.informatica.sii.entities.Grupos_asig;

@Named
@ViewScoped
@SuppressWarnings("unused")
public class AsignaturasBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private GestionAsignaturas ga = new AsignaturasImpl();

	@EJB
	private GestionAsignacion gas = new AsignacionImpl();

	private Asignatura asig;

	private Part part;
	private String fileName;
	private String mensaje;

	private String containerID;
	private String prevID;
	private int id;
	private String filtro;

	private List<Grupo> listaGrupos;
	private List<String> grupos;

	public AsignaturasBean() {
		asig = new Asignatura();
		mensaje = "";
		id = 0;
	}

	public List<Asignatura> getAsignaturas() {
		List<Asignatura> list = ga.listarAsignaturas();
		if (filtro == null)
			return list.stream().filter(a -> a.getTitulacion().getCodigo() == 1041).collect(Collectors.toList());
		switch (filtro) {
		case "GII":
			return list.stream().filter(a -> a.getTitulacion().getCodigo() == 1041).collect(Collectors.toList());
		case "GISw":
			return list.stream().filter(a -> a.getTitulacion().getCodigo() == 1042).collect(Collectors.toList());
		case "GIC":
			return list.stream().filter(a -> a.getTitulacion().getCodigo() == 1043).collect(Collectors.toList());
		case "GIS":
			return list.stream().filter(a -> a.getTitulacion().getCodigo() == 1056).collect(Collectors.toList());
		case "DG":
			return list.stream().filter(a -> a.getTitulacion().getCodigo() == 1073).collect(Collectors.toList());
		}
		return null;
	}

	public String eliminarAsignatura(Asignatura asig) throws SecretariaException {
		ga.borrarAsignatura(asig.getCodigo(), asig.getTitulacion().getCodigo());
		return null;
	}

	public String editarAsignatura(Asignatura a) throws SecretariaException {
		a.setCreditos_practicos(
				asig.getCreditos_practicos() != null ? asig.getCreditos_practicos() : a.getCreditos_practicos());
		a.setCreditos_teoricos(
				asig.getCreditos_teoricos() != null ? asig.getCreditos_teoricos() : a.getCreditos_teoricos());
		a.setIdiomas(asig.getIdiomas() != null ? asig.getIdiomas() : a.getIdiomas());
		ga.modificarAsignatura(a.getCodigo(), a.getTitulacion().getCodigo(), a);

		for (Grupos_asig g_a : a.getGrupos()) {
			ga.borrarDefinirGrupos(g_a);
		}
		for (String s : grupos) {
			Grupo g = gas.obtenerGrupo(s);
			ga.definirGrupos(a.getCodigo(), a.getTitulacion().getCodigo(), g.getId(), "20/21");
		}

		asig = new Asignatura();
		return null;
	}

	public String upload() throws IOException, SecretariaIOException, SecretariaException {

		String ruta = "";
		try {
			fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
			part.write(fileName);
			ruta = System.getProperty("jboss.server.temp.dir") + "/grupoE-ear.ear.grupoE-war-0.0.1-SNAPSHOT.war/"
					+ fileName;

			ga.importaAsignaturas(ruta);

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

	public String vaciarDatos() throws SecretariaException {
		for (Asignatura as : ga.listarAsignaturas()) {
			ga.borrarAsignatura(as.getCodigo(), as.getTitulacion().getCodigo());
		}
		mensaje = "Borrado correcto";
		return null;
	}

	public List<Grupo> listaGrupos(Integer titulacion, Integer curso) {
		return gas.listaGrupos().stream()
				.filter(a -> a.getTitulacion().getCodigo().equals(titulacion) && a.getCurso().equals(curso))
				.collect(Collectors.toList());
	}

	public String gruposAsignados(Asignatura a) {
		String res = "";
		for (Grupos_asig g : a.getGrupos()) {
			res += "(" + g.getGrupo().getCurso() + " " + g.getGrupo().getLetra() + ") ";
		}
		return res;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
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

	public Asignatura getAsig() {
		return asig;
	}

	public void setAsig(Asignatura asig) {
		this.asig = asig;
	}

	public String refresh() {
		return null;
	}

	public List<String> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<String> grupos) {
		this.grupos = grupos;
	}

}
