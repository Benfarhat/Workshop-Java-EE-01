package model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Order {
	
	@Id @GeneratedValue
	private Long id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "order_fk")
	private List<OrderLine> orderLines;
	public Order() {
		super();
	}
	public Order(Date creationDate, List<OrderLine> orderLines) {
		super();
		this.creationDate = creationDate;
		this.orderLines = orderLines;
	}
	public Order(Long id, Date creationDate, List<OrderLine> orderLines) {
		super();
		this.id = id;
		this.creationDate = creationDate;
		this.orderLines = orderLines;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public List<OrderLine> getOrderLines() {
		return orderLines;
	}
	public void setOrderLines(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}
	
   
}
