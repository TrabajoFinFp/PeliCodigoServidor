package model.beans;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the USUARIOS database table.
 * 
 */
@Entity
@Table(name="USUARIOS")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="MAIL")
	private String mail;

	@Column(name="PWD")
	private String pwd;

	@Column(name="TYPE_USER")
	private String typeUser;

	@Column(name="USERNAME")
	private String username;

	//bi-directional many-to-one association to Coleccion
	@OneToMany(mappedBy="usuario")
	private List<Coleccion> colecciones;

	public Usuario() {
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getTypeUser() {
		return this.typeUser;
	}

	public void setTypeUser(String typeUser) {
		this.typeUser = typeUser;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Coleccion> getColecciones() {
		return this.colecciones;
	}

	public void setColecciones(List<Coleccion> colecciones) {
		this.colecciones = colecciones;
	}

	public Coleccion addColeccione(Coleccion coleccione) {
		getColecciones().add(coleccione);
		coleccione.setUsuario(this);

		return coleccione;
	}

	public Coleccion removeColeccione(Coleccion coleccione) {
		getColecciones().remove(coleccione);
		coleccione.setUsuario(null);

		return coleccione;
	}

}