package service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
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

import entity.Meal;
import entity.Order;
import entity.Restaurant;
import entity.Runner;


@Stateless
@Path("/restaurant")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class restaurantService {
	@PersistenceContext
	EntityManager entityManager;
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
	@Path("/edit-Restaurant/{restaurantId}/{orderId}")
	@POST
	public Response createrestor(Meal meal,@PathParam("restaurantId") Long id2 ) {
		
        Restaurant restaurant = entityManager.find(Restaurant.class, id2);
        restaurant.setMeals(meal);
        meal.setByRestaurant(restaurant);
		entityManager.merge(restaurant);
		return Response.status(Response.Status.OK).entity(restaurant).type(MediaType.APPLICATION_JSON).build();

	}
	@Path("/get-restaurant-details/{resId}")
	@GET
	public Response getresById(Restaurant restaurant ,@PathParam("resId") Long id ) {
		Restaurant m = entityManager.find(Restaurant.class, id);
		if(m==null) {
			return Response.status(Response.Status.OK).entity("Not Found").type(MediaType.APPLICATION_JSON).build();
		}
		return Response.status(Response.Status.OK).entity(m).type(MediaType.APPLICATION_JSON).build();

	}
	@Path("/get-restaurant-report/{resId}")
	@GET
	public Response getReport(Restaurant restaurant ,@PathParam("resId") Long id ) {
		Restaurant m = entityManager.find(Restaurant.class, id);
		if(m==null) {
			return Response.status(Response.Status.OK).entity("Not Found").type(MediaType.APPLICATION_JSON).build();
		}
		return Response.status(Response.Status.OK).entity(m.getReport()).type(MediaType.APPLICATION_JSON).build();

	}
	@Path("/create-meal")
	@POST
	public Response creatMeal(Meal meal  ) {
		TypedQuery<Meal> query=entityManager.createQuery("SELECT m from Meal m where m.name=?1",Meal.class);
		query.setParameter(1, meal.getName());
        List<Meal> u =query.getResultList();
         if (u.size()!=0) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Restaurant with the same name").type(MediaType.APPLICATION_JSON).build();
	     }
        entityManager.persist(meal);
		return Response.status(Response.Status.OK).entity(meal).type(MediaType.APPLICATION_JSON).build();

	}
}
