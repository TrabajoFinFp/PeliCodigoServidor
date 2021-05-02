package model.beans;

import java.io.Serializable;
import javax.persistence.*;


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

}