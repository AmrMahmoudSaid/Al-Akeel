package repo;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entity.Order;

@Stateless

public class OrderRepo {
	 @PersistenceContext
	    private EntityManager entityManager;
	    
	    public Order findById(int id) {
	        return entityManager.find(Order.class, id);
	    }
	    
	    public List<Order> findAll() {
	        TypedQuery<Order> query = entityManager.createQuery("SELECT o FROM Order o", Order.class);
	        return query.getResultList();
	    }
	    
	    public void save(Order order) {
	        entityManager.persist(order);
	    }
	    
	    public void update(Order order) {
	        entityManager.merge(order);
	    }
	    
	    public void delete(Order order) {
	        entityManager.remove(entityManager.merge(order));
	    }
}
