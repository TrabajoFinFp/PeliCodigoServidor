package servicios;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import model.beans.Coleccion;
import model.beans.CollecionPelicula;
import model.beans.Pelicula;
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
		
		try {

			
		query = em.createNativeQuery("SELECT ID_COLECCIONES FROM COLECCIONES  WHERE MAIL = ?");
		query.setParameter(1, email);
		List<Integer> resultado = query.getResultList();
		List<Coleccion> listacoleccion = new ArrayList<>();
		
		 for (int i = 0; i < resultado.size(); i++) {
				listacoleccion.add(em.find(Coleccion.class, ""+query.getResultList().get(i)));
	        }
		return listacoleccion;
		}catch(Exception e) {
			return null;
			}
	}
	@GET
	@Path("/obtenerPeliculasDeColeccion")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pelicula> obtenerPeliculasDeColeccion(@QueryParam("coleccionid") String coleccionid) {
		
		try {

		query = em.createNativeQuery("SELECT IMDBID FROM COLLECIONES_PELICULAS  WHERE ID_COLECCIONES =  ?");
		query.setParameter(1, coleccionid);
		List<Integer> resultado =query.getResultList();
		List<Pelicula> listapelicula = new ArrayList<>();

		 for (int i = 0; i < resultado.size(); i++) {
			 listapelicula.add(em.find(Pelicula.class, ""+query.getResultList().get(i)));
	        }
		return listapelicula;
		}catch(Exception e) {
			return null;
			}
	}
	
	
	
	
	
	@POST
	@Path("/newList")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean nuevaLista(@QueryParam("email") String email,@QueryParam("nameCollection") String nameCollection) {
		Usuario usuario = new Usuario();
		Coleccion coleccion = new Coleccion();
		coleccion.setNameCollection(nameCollection);
		System.out.println("paso 1");
		try {
			System.out.println("paso 2");
		usuario = em.find(Usuario.class, email);
		coleccion.setUsuario(usuario);
		System.out.println("paso 3");
		//usuario.addColeccione(coleccion);
		System.out.println("paso 4");
		tx.begin();
		em.persist(coleccion);
		tx.commit();
		return true;
		}catch(Exception e) {
			return false;
			}
	}
	
	@POST
	@Path("/deleteList")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean borrarLista(@QueryParam("coleccionid") String coleccionid) {
		
		System.out.println(coleccionid);
		try {
			
			Coleccion borrarColeccion = em.find(Coleccion.class, coleccionid);
			em.getTransaction().begin();
			  em.remove(borrarColeccion);
			  em.getTransaction().commit();
			
			
			
		return true;
		}catch(Exception e) {
			System.out.println(e);
			return false;
			}
	}
	@POST
	@Path("/addPeliculaToLista")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean addPeliculaToLista(@QueryParam("coleccionid") String coleccionid, @QueryParam("imdbid") String imdbid) {
		
		System.out.println(coleccionid);
		try {
			CollecionPelicula colleccionpelicula = new CollecionPelicula();
			colleccionpelicula.setColeccione(em.find(Coleccion.class, coleccionid));
			Pelicula pelicula = new Pelicula();
			pelicula.setImdbid(imdbid);
			/*tx.begin();
			em.persist(pelicula);
			tx.commit();*/
			tx.begin();
			em.merge(pelicula);
			tx.commit();
			colleccionpelicula.setPelicula(pelicula);
			tx.begin();
			em.persist(colleccionpelicula);
			tx.commit();
			
			
		return true;
		}catch(Exception e) {
			System.out.println(e);
			return false;
			}
	}
	@POST
	@Path("/removePeliculaFromLista")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean removePeliculaFromLista(@QueryParam("coleccionid") String coleccionid, @QueryParam("imdbid") String imdbid) {
		
		System.out.println(coleccionid);
		try {

			query = em.createNativeQuery("SELECT ID_COLLECIONES_PELICULAS FROM COLLECIONES_PELICULAS  where ID_COLECCIONES = ? AND IMDBID = ?");
			query.setParameter(1, coleccionid);
			query.setParameter(2, imdbid);
			
			CollecionPelicula collepeli = em.find(CollecionPelicula.class, ""+query.getResultList().get(0));
			//em.remove((CollecionPelicula));
				em.getTransaction().begin();
			  em.remove(collepeli);
			  em.getTransaction().commit();
			
		return true;
		}catch(Exception e) {
			System.out.println(e);
			return false;
			}
	}
	
	
	@POST
	@Path("/prueba")
	@Produces(MediaType.APPLICATION_JSON)
	public int prueba() {
		int algo = 2;
		return algo;
	}
	
	
	
}
