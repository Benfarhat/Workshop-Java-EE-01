import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll; 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.Address;
import model.Customer;

class TestCustomerAddress {
	private static EntityManagerFactory emf;
    private static EntityManager em;
    private static EntityTransaction tx;
    
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    	emf = Persistence.createEntityManagerFactory("unitNameForTest");
    	em = emf.createEntityManager();
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        em.close();
        emf.close();
    }

    @BeforeEach
    void setUp() throws Exception {
        tx = em.getTransaction();
    }

    @AfterEach
    void tearDown() throws Exception {
    }


    @DisplayName("Test Customer creation")
    @Test
    void testCustomerCreation() {
    	
    	Customer customer = new Customer("John", "Doe", "John.Doe@example.com");
    	Address address = new Address("stree 1", "street 2", "city", "state", "4KM", "Toronto");
    	customer.setAddress(address);

        tx.begin();
        em.persist(customer);
        tx.commit();
        
        @SuppressWarnings("unchecked")
		List<Customer> customers = em.createNamedQuery("Customer.findAll").getResultList();
        assertAll(
        		() -> assertNotNull(
        				customer.getId(), 
        				() -> "ID should not be null"),
        		() -> assertSame(
        				(Customer) em.createNamedQuery("Customer.findById").setParameter("id", customer.getId()).getSingleResult(),
        				em.find(Customer.class,  customer.getId()),
        				() -> "different find methods should get the same result"),
        		() -> assertFalse(
        				customer.getFirstName().isEmpty(), 
        				() -> "FirstName should not be empty"),
        		() -> assertNotNull(
        				customers, 
        				() -> "Result list should not be null")
        );
    }

}
