package repo;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entity.Meal;

@Stateless
public class MealRepo {
	@PersistenceContext
    private EntityManager entityManager;
    
    public Meal findById(int id) {
        return entityManager.find(Meal.class, id);
    }
    
    public List<Meal> findAll() {
        TypedQuery<Meal> query = entityManager.createQuery("SELECT m FROM MealModel m", Meal.class);
        return query.getResultList();
    }
    
    public void save(Meal meal) {
        entityManager.persist(meal);
    }
    
    public void update(Meal meal) {
        entityManager.merge(meal);
    }
    
    public void delete(Meal meal) {
        entityManager.remove(entityManager.merge(meal));
    }
}
