package servicios;
import java.util.List;

import javax.persistence.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import model.beans.Coleccion;
import model.beans.Usuario;

@Path("/api")
public class Api {

	protected EntityManagerFactory emf;
	protected EntityManager em;
	protected EntityTransaction tx;
	protected String sql;
	protected Query query;
	public Api() {
		super();
		emf = Persistence.createEntityManagerFactory("PeliCodigoServidor");
		em = emf.createEntityManager();
		tx = em.getTransaction();
		
	}
	
	@POST
	@Path("/registrar")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean registrarUsuario(@QueryParam("email") String email, @QueryParam("password") String password,
			@QueryParam("username") String username) {
		Usuario usuario = new Usuario();
		usuario.setMail(email);
		usuario.setPwd(password);
		usuario.setTypeUser("normal");
		usuario.setUsername(username);
		
		try {
			tx.begin();
			em.persist(usuario);
			tx.commit();
			return true;
			}catch(Exception e) {
			return false;
			}
	}
	
	@GET
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario loginusuario(@QueryParam("email") String email, @QueryParam("password") String password) {
		Usuario usuario = new Usuario();
		try {
		usuario = em.find(Usuario.class, email);
		if(usuario.getPwd().equals(password)) {
			return usuario;
		}
		else {
			return null;
		}
		}catch(Exception e) {
			return null;
			}
	}
	@GET
	@Path("/obtenerCollecciones")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Coleccion> obtenerCollecciones(@QueryParam("email") String email) {
		Usuario usuario = new Usuario();
		try {
		usuario = em.find(Usuario.class, email);
		usuario.getColecciones();
		
			return usuario.getColecciones();
		
			
		
		}catch(Exception e) {
			return null;
			}
	}
	
	
}
