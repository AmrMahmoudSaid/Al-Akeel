package service;

import java.util.HashMap;
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

import entity.Order;
import entity.Restaurant;
import entity.Runner;

@Stateless
@Path("/runner")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class runnerService {
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Path("order-done/{id}")
	@GET
	public Response makeOrderDone(@PathParam("id") Long id) {
		Runner r = entityManager.find(Runner.class,id);
		if(r == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("No runner with this id").type(MediaType.APPLICATION_JSON).build();
		}
		if(r.getStatus().equals("busy")) {
			Order order = r.getOrder();
			Restaurant restaurant =  order.getOrderBy();
			restaurant.setCompletedOrders();
			order.setTotal_amount();
			restaurant.setRestaurantEarns(order.getamount());
			r.setStatus("available");
			r.setNumberOfOrder();
			entityManager.merge(r);
		}
		
		return Response.status(Response.Status.OK).entity(r).type(MediaType.APPLICATION_JSON).build();
	}
	@Path("number-of-order/{id}")
	@GET
	public Response  getNumberOfOrder(@PathParam("id") Long id) {
		Runner r = entityManager.find(Runner.class,id);
		if(r == null) {
			return Response.status(Response.Status.OK).entity("Not exist").type(MediaType.APPLICATION_JSON).build();
		}else {
			return Response.status(Response.Status.OK).entity(r.getNumberOfOrder()).type(MediaType.APPLICATION_JSON).build();

		}
	}
    

}
