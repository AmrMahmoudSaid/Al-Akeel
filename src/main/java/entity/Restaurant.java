package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
public class Restaurant {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	private String name;
	private Long ownerId;
	@Transient
	private int completedOrders = 0;
	@Transient
	private int canceledOrders = 0;
	@Transient
	private double total_amount = 0;
	@Transient
	@OneToMany(mappedBy="byRestaurant", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Meal> meals = new ArrayList<>();
	@Transient
	@OneToMany(mappedBy="orderBy", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Order> orders = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public List<Meal> getMeals() {
		return meals;
	}

	public void setMeals(List<Meal> meals) {
		this.meals=meals;
	}

	public List<Order> getOrders() {
		return orders;
	}
	public void addMeal(Meal meal) {
		this.meals.add(meal);
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public int getCompletedOrders() {
		return completedOrders;
	}

	public void setCompletedOrders() {
		this.completedOrders += completedOrders ;
	}

	public int getCanceledOrders() {
		return canceledOrders;
	}

	public void setCanceledOrders() {
		this.canceledOrders += canceledOrders;
	}

	public double total_amount() {
		return this.total_amount;
	}

	public void setRestaurantEarns(double total_amount) {
		this.total_amount = total_amount;
	}

	public String getReport() {
		String report = "Restaurant Name : "+this.name+"\n"+"Number Of completed order :"+ this.completedOrders+"\n"+
				"Number Of Canceled order :"+ this.canceledOrders+"\n"+
				"Total amount :"+this.total_amount;
		return report;
	}

	

}
