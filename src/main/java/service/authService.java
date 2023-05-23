package service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import entity.Runner;
import entity.User;


@Stateless
@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class authService {
	
	@PersistenceContext
	EntityManager entityManager;
	
    @POST
	@Path("/sign-up")
    public Response signUp(User user)  {
    	TypedQuery<User> query=entityManager.createQuery("SELECT u from User u where u.name=?1",User.class);
		query.setParameter(1, user.getName());
        List<User> u =query.getResultList();
         if (u.size()!= 0) {
        	
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(null).type(MediaType.APPLICATION_JSON).build();
	     }
		 entityManager.persist(user);
		return Response.status(Response.Status.OK).entity(user).type(MediaType.APPLICATION_JSON).build();
    }
    @GET
    @Path("/get-user/{id}")
    public Response getUser(@PathParam("id") Long id)  {
    	User u =entityManager.find(User.class, id);
		return Response.status(Response.Status.OK).entity(u).type(MediaType.APPLICATION_JSON).build();

    }
    
    @Path("sign-up-runner")
	@POST
	public Response signUpRunner(Runner runner) {
    	TypedQuery<Runner> query=entityManager.createQuery("SELECT r from Runner r where r.name=?1",Runner.class);
		query.setParameter(1, runner.getName());
        List<Runner> u =query.getResultList();
         if (u.size()!= 0) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(null).type(MediaType.APPLICATION_JSON).build();
	     }
		 entityManager.persist(runner);
		return Response.status(Response.Status.OK).entity(runner).type(MediaType.APPLICATION_JSON).build();

	}
}