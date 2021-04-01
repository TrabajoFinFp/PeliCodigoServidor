package model.beans;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PELICULAS database table.
 * 
 */
@Entity
@Table(name="PELICULAS")
@NamedQuery(name="Pelicula.findAll", query="SELECT p FROM Pelicula p")
public class Pelicula implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="IMDBID")
	private String imdbid;

	//bi-directional many-to-one association to CollecionPelicula
	@OneToMany(mappedBy="pelicula")
	private List<CollecionPelicula> collecionesPeliculas;

	public Pelicula() {
	}

	public String getImdbid() {
		return this.imdbid;
	}

	public void setImdbid(String imdbid) {
		this.imdbid = imdbid;
	}

	public List<CollecionPelicula> getCollecionesPeliculas() {
		return this.collecionesPeliculas;
	}

	public void setCollecionesPeliculas(List<CollecionPelicula> collecionesPeliculas) {
		this.collecionesPeliculas = collecionesPeliculas;
	}

	public CollecionPelicula addCollecionesPelicula(CollecionPelicula collecionesPelicula) {
		getCollecionesPeliculas().add(collecionesPelicula);
		collecionesPelicula.setPelicula(this);

		return collecionesPelicula;
	}

	public CollecionPelicula removeCollecionesPelicula(CollecionPelicula collecionesPelicula) {
		getCollecionesPeliculas().remove(collecionesPelicula);
		collecionesPelicula.setPelicula(null);

		return collecionesPelicula;
	}

}