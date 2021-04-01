package servicios;


import javax.persistence.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import model.beans.Usuario;

@Path("/prueba")
public class Prueba {
	protected EntityManagerFactory emf;
	protected EntityManager em;
	protected EntityTransaction tx;
	protected String sql;
	protected Query query;
	public Prueba() {
		super();
		emf = Persistence.createEntityManagerFactory("PeliCodigoServidor");
		em = emf.createEntityManager();
		tx = em.getTransaction();
		
	}
	
	@GET
	@Path("/algo")
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario obtenerUsuario() {
		
		Usuario usuario = new Usuario();
		try {
		usuario = em.find(Usuario.class, "prueba@prueba.es");
		return usuario;
		}catch(Exception e) {
			return null;
			}
		
		
	}
	
	
}
