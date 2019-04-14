package model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
@NamedQueries({
    @NamedQuery(name="Customer.findAll",
            query="SELECT c FROM Customer c"),
    @NamedQuery(name="Customer.countAll",
    		query="SELECT COUNT(c) FROM Customer c"),
    @NamedQuery(name="Customer.findById",
    	query="SELECT c FROM Customer c WHERE c.id = :id"),
    @NamedQuery(name="Customer.findByFirstName",
		query="SELECT c FROM Customer c WHERE c.firstName = :firstName"),
    @NamedQuery(name="Customer.findByLastName",
		query="SELECT c FROM Customer c WHERE c.lastName = :lastName"),
    @NamedQuery(name="Customer.findByEmail",
		query="SELECT c FROM Customer c WHERE c.email = :email")
}) 
public class Customer implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private Long id;
	@Column( name = "first_name", nullable = false, length = 50)
	private String firstName;
	@Column( name = "last_name", nullable = false, length = 50)
	private String lastName;
	private String email;
	@Column( name = "phone_number", length = 20)
	private String phoneNumber;
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
	@JoinColumn(nullable = false)
	private Address address;
	
	

	public Customer(String firstName, String lastName, String email, String phoneNumber, Address address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}

	public Customer(String firstName, String lastName, String email, String phoneNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public Customer(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	public Customer(Long id, String firstName, String lastName, String email, String phoneNumber, Address address) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}

	public Customer() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
   
	
}
