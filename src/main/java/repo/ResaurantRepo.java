package repo;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entity.Restaurant;

@Stateless

public class ResaurantRepo {
	@PersistenceContext
    private EntityManager entityManager;
	public ResaurantRepo() {}
	public void insert(Restaurant restaurant) {
        entityManager.persist(restaurant);
    }
	 public Restaurant getRestaurantById(int id) {
	        return entityManager.find(Restaurant.class, id);
	 }
	 public List<Restaurant> findAll() {
	        TypedQuery<Restaurant> query = entityManager.createQuery("SELECT r FROM Restaurant r", Restaurant.class);
	        return query.getResultList();
	 }
	    
	 public void save(Restaurant restaurant) {
		 entityManager.persist(restaurant);
	 }
	    
	 public void update(Restaurant restaurant) {
	        entityManager.merge(restaurant);
	 }
	    
	 public void delete(Restaurant restaurant) {
	        entityManager.remove(entityManager.merge(restaurant));
	 }
}
