package entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity 
public class Order {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	private double total_amount;
	private String status="preparing";
	private LocalDateTime date = LocalDateTime.now();
	@Transient
	@OneToMany(mappedBy="order")
	private List<Meal> meals = new ArrayList<>();
	
	@Transient
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="restaurantId")
	private Restaurant orderBy;

	@OneToOne(optional = false)
	@JoinColumn(name="runnerId")
	private Runner runner;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public List<Meal> getMeals() {
		return meals;
	}

	public void setMeals(List<Meal> meals) {
		this.meals = meals;
	}

	public Restaurant getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Restaurant orderBy) {
		this.orderBy = orderBy;
	}

	public Runner getRunner() {
		return runner;
	}

	public void setRunner(Runner runner) {
		this.runner = runner;
	}

	public double getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount() {
		this.total_amount=0;
		Iterator<Meal> namesIterator = this.meals.iterator();
		while(namesIterator.hasNext()) {
			Meal m = namesIterator.next();
			this.total_amount+=m.getPrice();
		}
	}
	
}
