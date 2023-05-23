package repo;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entity.Runner;

@Stateless

public class RunnerRepo {
	 	@PersistenceContext
	    private EntityManager entityManager;
	    
	    public Runner findById(int id) {
	        return entityManager.find(Runner.class, id);
	    }
	    
	    public List<Runner> findAll() {
	        TypedQuery<Runner> query = entityManager.createQuery("SELECT r FROM Runner r", Runner.class);
	        return query.getResultList();
	    }
	    
	    public void save(Runner runner) {
	        entityManager.persist(runner);
	    }
	    
	    public void update(Runner runner) {
	        entityManager.merge(runner);
	    }
	    
	    public void delete(Runner runner) {
	        entityManager.remove(entityManager.merge(runner));
	    }
}
