import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.Address;
import model.Customer;

@DisplayName("Testing customer and address entities")
class TestCustomerAddress {
	private static EntityManagerFactory emf;
    private static EntityManager em;
    private static EntityTransaction tx;
    private static Logger log = Logger.getLogger(TestCustomerAddress.class);
    
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    	log.info("Setup class");
    	emf = Persistence.createEntityManagerFactory("unitNameForTest");
    	em = emf.createEntityManager();
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    	log.info("Clean class");
        em.close();
        emf.close();
    }

    @BeforeEach
    void setUp() throws Exception {
    	log.info("Setup");
        tx = em.getTransaction();
    }

    @AfterEach
    void tearDown() throws Exception {
    	log.info("Clean");
    }


    @DisplayName("Test Customer creation")
    @Test
    void testCustomerCreation() {

    	Customer customer = new Customer("Michel", "J.West", "Michel.JWest@example.com");
    	Address address = new Address("4240  Fourth Avenue", "", "Calgary", "Alberta", "T2P", "Canada");
    	customer.setAddress(address);
    	
        tx.begin();
        em.persist(customer);
        em.persist(address);
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
    

    @DisplayName("Test Orphan deletion")
    @Test
    void testOrphandeletion() {

    	Customer customer = new Customer("Kelsey", "Reid", "Kelsey.PReid@example.com");
    	Address address = new Address("1256  Cordova Street", "", "Vancouver", "British Columbia", "V6B 1E1", "Canada");
    	customer.setAddress(address);
    	
        tx.begin();
        em.persist(customer);
        em.persist(address);
        tx.commit();
        
        Long indexC = customer.getId();
        Long indexA = address.getId();

        assertTrue(em.contains(customer), () -> "Customer should be managed by em");
        assertTrue(em.contains(address), () -> "Address should be managed by em");
        assertNotNull(
        		em.find(Address.class, indexA),
        		() -> "We should find address with id: " 
        				+ indexA 
        				+ ", his address is joined with customer id: "
        				+ indexC
        		); 
        
        em.remove(customer);
        assertFalse(em.contains(customer), () -> "Customer should not be managed by em after a remove action");
        assertFalse(em.contains(address), () -> "Orphan Address should not be be managed by em after a remove action on its Customer");
        assertNull(
        		em.find(Address.class, indexA),
        		() -> "We should not find orphan address with id: " 
        				+ indexA 
        				+ ", after deleting its corresponding customer."
        		);
    }


}
