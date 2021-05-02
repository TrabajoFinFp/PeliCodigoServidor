package model.beans;

import java.io.Serializable;
import javax.persistence.*;


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

	public Pelicula() {
	}

	public String getImdbid() {
		return this.imdbid;
	}

	public void setImdbid(String imdbid) {
		this.imdbid = imdbid;
	}

}