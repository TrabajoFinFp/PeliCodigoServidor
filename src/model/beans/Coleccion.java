package model.beans;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the COLECCIONES database table.
 * 
 */
@Entity
@Table(name="COLECCIONES")
@NamedQuery(name="Coleccion.findAll", query="SELECT c FROM Coleccion c")
public class Coleccion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_COLECCIONES")
	private String idColecciones;

	@Column(name="NAME_COLLECTION")
	private String nameCollection;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="MAIL")
	private Usuario usuario;

	//bi-directional many-to-one association to CollecionPelicula
	@OneToMany(mappedBy="coleccione")
	private List<CollecionPelicula> collecionesPeliculas;

	public Coleccion() {
	}

	public String getIdColecciones() {
		return this.idColecciones;
	}

	public void setIdColecciones(String idColecciones) {
		this.idColecciones = idColecciones;
	}

	public String getNameCollection() {
		return this.nameCollection;
	}

	public void setNameCollection(String nameCollection) {
		this.nameCollection = nameCollection;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<CollecionPelicula> getCollecionesPeliculas() {
		return this.collecionesPeliculas;
	}

	public void setCollecionesPeliculas(List<CollecionPelicula> collecionesPeliculas) {
		this.collecionesPeliculas = collecionesPeliculas;
	}

	public CollecionPelicula addCollecionesPelicula(CollecionPelicula collecionesPelicula) {
		getCollecionesPeliculas().add(collecionesPelicula);
		collecionesPelicula.setColeccione(this);

		return collecionesPelicula;
	}

	public CollecionPelicula removeCollecionesPelicula(CollecionPelicula collecionesPelicula) {
		getCollecionesPeliculas().remove(collecionesPelicula);
		collecionesPelicula.setColeccione(null);

		return collecionesPelicula;
	}

}