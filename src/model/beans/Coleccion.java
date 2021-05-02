package model.beans;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


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

	//uni-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="MAIL")
	private Usuario usuario;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "coleccione", orphanRemoval = true)
	private List<CollecionPelicula> colecionpelicula;
	
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

}