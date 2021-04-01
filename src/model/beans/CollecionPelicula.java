package model.beans;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the COLLECIONES_PELICULAS database table.
 * 
 */
@Entity
@Table(name="COLLECIONES_PELICULAS")
@NamedQuery(name="CollecionPelicula.findAll", query="SELECT c FROM CollecionPelicula c")
public class CollecionPelicula implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_COLLECIONES_PELICULAS")
	private String idCollecionesPeliculas;

	//bi-directional many-to-one association to Coleccion
	@ManyToOne
	@JoinColumn(name="ID_COLECCIONES")
	private Coleccion coleccione;

	//bi-directional many-to-one association to Pelicula
	@ManyToOne
	@JoinColumn(name="IMDBID")
	private Pelicula pelicula;

	public CollecionPelicula() {
	}

	public String getIdCollecionesPeliculas() {
		return this.idCollecionesPeliculas;
	}

	public void setIdCollecionesPeliculas(String idCollecionesPeliculas) {
		this.idCollecionesPeliculas = idCollecionesPeliculas;
	}

	public Coleccion getColeccione() {
		return this.coleccione;
	}

	public void setColeccione(Coleccion coleccione) {
		this.coleccione = coleccione;
	}

	public Pelicula getPelicula() {
		return this.pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

}