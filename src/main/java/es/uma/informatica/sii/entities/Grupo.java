package es.uma.informatica.sii.entities;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Grupo
 *
 */
@Entity

public class Grupo implements Serializable {

	@Id
	@Column(nullable=false)
	private String id;
	@Column(length=1, nullable=false)
	private Integer curso;
	@Column(length=1, nullable=false)
	private Boolean letra;
	@Column(length=6, nullable=false)
	private String turno;
	@Column(length=2, nullable=false)
	private Boolean inglés;
	private Boolean visible;
	@Column(length=3)
	private Integer plazas;
	private String asignar;
	
	@OneToMany(mappedBy="grupo_id")
	private List<Grupo> grupos;
	@ManyToOne
	private Grupo grupo_id;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Titulacion titulación_código;
	
	@OneToMany(mappedBy="asign_grupo")
	private List<Asigna_Grupos> asignagrupos;
	
	private static final long serialVersionUID = 1L;

	public Grupo() {
		super();
	}   
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}   
	public Integer getCurso() {
		return this.curso;
	}

	public void setCurso(Integer curso) {
		this.curso = curso;
	}   
	public String getTurno() {
		return this.turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}   
	
	public Boolean getLetra() {
		return this.letra;
	}

	public void setLetra(Boolean letra) {
		this.letra = letra;
	}
	
	public Boolean getInglés() {
		return this.inglés;
	}

	public void setInglés(Boolean inglés) {
		this.inglés = inglés;
	}   
	public Boolean getVisible() {
		return this.visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}   
	public Integer getPlazas() {
		return this.plazas;
	}

	public void setPlazas(Integer plazas) {
		this.plazas = plazas;
	}   
	public String getAsignar() {
		return this.asignar;
	}

	public void setAsignar(String asignar) {
		this.asignar = asignar;
	}   
	
	public List<Grupo> getGrupos() {
		return grupos;
	}
	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}
	public Grupo getGrupo_id() {
		return grupo_id;
	}
	public void setGrupo_id(Grupo grupo_id) {
		this.grupo_id = grupo_id;
	}
	public Titulacion getTitulación_código() {
		return this.titulación_código;
	}

	public void setTitulación_código(Titulacion titulación_código) {
		this.titulación_código = titulación_código;
	}   
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grupo other = (Grupo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
   
}
