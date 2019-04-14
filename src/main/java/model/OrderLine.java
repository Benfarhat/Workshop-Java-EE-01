package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "order_line")
public class OrderLine implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private Long id;
	private String item;
	private Double unitPrice;
	private Integer quantity;

	public OrderLine() {
		super();
	}

	public OrderLine(String item, Double unitPrice, Integer quantity) {
		super();
		this.item = item;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
	}

	public OrderLine(Long id, String item, Double unitPrice, Integer quantity) {
		super();
		this.id = id;
		this.item = item;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
   
}
