package repo;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.User;

@Stateless
public class UserRepo {
	@PersistenceContext(unitName="hello")
    private EntityManager entityManager;
	public UserRepo() {}
	public void insert(User user) {
		System.out.print(user);
        entityManager.persist(user);
    }
	 public User getUserById(int id) {
	        return entityManager.find(User.class, id);
	 }
	public Object findByName(String name) {
		return entityManager.find(User.class, name);
	}
}
