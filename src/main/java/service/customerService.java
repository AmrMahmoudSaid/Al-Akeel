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

import entity.Meal;
import entity.Order;
import entity.Restaurant;
import entity.Runner;
import entity.User;


@Stateless
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class customerService {
//	@Inject
//	private UserRepo userRepo ;
//	@Inject
//	private ResaurantRepo restaurantRepo;
//	@Inject
//	private OrderRepo orderRepo;
//	@Inject
//	private RunnerRepo runnerRepo;
//	@Inject
//	private RunnerUtility runnrtUtility;
//	@Inject
//	private ResaurantRepo resaurantRepo;
	@PersistenceContext
	EntityManager entityManager;
	
	@Path("/creat-order")
	@POST
	public Response createOrder(Order order) {
		TypedQuery<Runner> query = entityManager.createQuery("SELECT r FROM Runner r", Runner.class);
		List<Runner> runnerModels = query.getResultList();
		for(int i = 0 ;i< runnerModels.size();i++) {
			Runner runner = runnerModels.get(i);
			if(runner.getStatus().equals("avaliable"))
			{
				System.out.print(runnerModels.get(i).getStatus());
				runnerModels.get(i).setStatus("busy");
				System.out.print(runnerModels.get(i).getStatus());
				runnerModels.get(i).setOrder(order);
				return Response.status(Response.Status.OK).entity(runner).type(MediaType.APPLICATION_JSON).build();
			}
		}
		return Response.status(Response.Status.NOT_FOUND).entity("No Runner avaliable").type(MediaType.APPLICATION_JSON).build();
	}
	@Path("/creat-order-by-id/{id}")
	@POST
	public Response createOrderByid(@PathParam("id") Long id ) {
		Order o= new Order();
		o.setMealls(entityManager.find(Meal.class, id));
		return createOrder(o);
		
		
	}
	@Path("/list-restaurants")
	@GET
	public Response getAllResaurant(){
		TypedQuery<Restaurant> query = entityManager.createQuery("SELECT r FROM Restaurant r", Restaurant.class);
		if(query.getResultList().size()==0) {
			return Response.status(Response.Status.OK).entity("message : no Restaurant find").type(MediaType.APPLICATION_JSON).build();

		}
		return Response.status(Response.Status.OK).entity(query.getResultList()).type(MediaType.APPLICATION_JSON).build();
	}
	@Path("/edit-order/{id}")
	@POST
	public Response editOrder(Order order,@PathParam("id") Long id ) {
		Order tempOrder = entityManager.find(Order.class,id);
		if(tempOrder.getStatus().equals("canceled")) {
			return Response.status(Response.Status.NOT_FOUND).entity("order is canceled aready").type(MediaType.APPLICATION_JSON).build();
		}else if(tempOrder.getStatus().equals("preparing")) {
			entityManager.merge(order);
			return Response.status(Response.Status.OK).entity(order).type(MediaType.APPLICATION_JSON).build();
		}
		return Response.status(Response.Status.NOT_FOUND).entity("Not Found").type(MediaType.APPLICATION_JSON).build();
	}

	
	@Path("/creat-Restaurant")
	@POST
	public Response createRestaurant(Restaurant restaurant) {
		TypedQuery<Restaurant> query=entityManager.createQuery("SELECT r from Restaurant r where r.name=?1",Restaurant.class);
		query.setParameter(1, restaurant.getName());
        List<Restaurant> u =query.getResultList();
         if (u.size()!=0) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Restaurant with the same name").type(MediaType.APPLICATION_JSON).build();
	     }
		entityManager.persist(restaurant);
		return Response.status(Response.Status.NOT_FOUND).entity(restaurant).type(MediaType.APPLICATION_JSON).build();
	}
    
}