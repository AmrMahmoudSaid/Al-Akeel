package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class Runner{
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

	private String name;
	private String status = "avaliable";
	private double fees;
	private int completedTrips = 0;
	
	@Transient
	@OneToOne(optional = false,mappedBy="runner")
	private Order order;

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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	

	public double getFees() {
		return fees;
	}

	public void setFees(double fees) {
		this.fees = fees;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCompletedTrips() {
		return completedTrips;
	}

	public void setCompletedTrips(int completedTrips) {
		this.completedTrips = completedTrips;
	}


}
