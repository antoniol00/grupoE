package es.uma.informatica.sii.backing;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import es.uma.informatica.sii.ejb.GestionMatriculas;
import es.uma.informatica.sii.ejb.MatriculasImpl;
import es.uma.informatica.sii.ejb.exceptions.SecretariaException;
import es.uma.informatica.sii.entities.Matricula;

@Named
@ViewScoped
@SuppressWarnings("unused")
public class MatriculasBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private GestionMatriculas mat = new MatriculasImpl();

	private String containerID;
	private String prevID;
	private int id;
	private String filtro;

	public MatriculasBean() {
		id = 0;
	}

	public List<Matricula> getMatriculas() throws SecretariaException {
		if (filtro==null) {
			setFiltro("EXP");
		}
		return mat.listaMatriculas(filtro);
	}

	public String getContainerID() {
		id++;
		return "#containerID" + id;
	}

	public String getPrevID() {
		return "containerID" + id;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public String refresh() {
		return null;
	}

}
