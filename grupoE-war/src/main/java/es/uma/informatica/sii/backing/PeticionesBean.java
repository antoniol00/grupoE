package es.uma.informatica.sii.backing;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import es.uma.informatica.sii.ejb.AlumnosImpl;
import es.uma.informatica.sii.ejb.GestionAlumnos;
import es.uma.informatica.sii.ejb.GestionPeticiones;
import es.uma.informatica.sii.ejb.PeticionesImpl;
import es.uma.informatica.sii.ejb.exceptions.SecretariaException;
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
	private String containerID;
	private String prevID;
	private int id;

	public PeticionesBean() {
		id = 0;
		p = new Peticion();
	}

	public List<Peticion> getPeticiones() {
		return pet.listaPeticiones();
	}

	public String crearPeticion() throws SecretariaException {
		p.setDate(new Date());
		p.setAlumno(al.obtenerAlumno(DNI));
		pet.creaIncidencia(p);
		p = new Peticion();
		DNI = "";
		return null;
	}

	public String eliminarPeticion(Peticion peticion) throws SecretariaException {
		pet.borraIncidencia(peticion.getDate(), peticion.getAlumno().getDni());
		return null;
	}

	public String editarPeticion(Peticion peticion) throws SecretariaException {
		p.setDate(peticion.getDate());
		p.setAlumno(peticion.getAlumno());
		pet.editaIncidencia(peticion.getDate(), peticion.getAlumno().getDni(), p);
		p = new Peticion();
		return null;
	}

	public String getAsignarDNI() {
		return DNI;
	}

	public void setAsignarDNI(String dni) {
		this.DNI = dni;
	}

	public Peticion getPeticion() {
		return p;
	}

	public String getContainerID() {
		id++;
		return "#containerID" + id;
	}

	public String getPrevID() {
		return "containerID" + id;
	}

}
