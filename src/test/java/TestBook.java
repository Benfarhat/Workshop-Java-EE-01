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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import model.Book;

@DisplayName("Testing book entity")
class TestBook {
    
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


    @DisplayName("Create Multiple Book with Parameterized")
    @ParameterizedTest(name = "{index}: {0}")
    @ValueSource(strings = {
    		"DO ANDROIDS DREAM OF ELECTRIC SHEEP?",
    		"EVERYTHING I NEVER TOLD YOU",
    		"THE DEVIL WEARS PRADA",
    		"THE CURIOUS INCIDENT OF THE DOG IN THE NIGHT-TIME",
    		"HOW TO WIN FRIENDS AND INFLUENCE PEOPLE",
    		"CLOUDY WITH A CHANCE OF MEATBALLS"
    		
    })
    void multipleCreateBook(String title) {

        Book book = new Book();
        book.setTitle(title);
        book.setPrice(12.5F);
        book.setDescription("This is a short description of this test book");
        book.setIsbn("1-808-bnh-35");
        book.setNbOfPage(350);
        book.setIllustrations(false);
        
        tx.begin();
        em.persist(book);
        tx.commit();
  
        assertNotNull(book.getId(), "L'ID ne doit pas être null");

        @SuppressWarnings("unchecked")
		List<Book> listBook = em.createNamedQuery("Book.findAll").getResultList();
		Book getBookByTitle = (Book) em.createNamedQuery("Book.findByTitle").setParameter("title", title).getSingleResult();
		
		assertAll(
				() -> assertNotNull(listBook, () -> "listBook should not be empty"),
				() -> assertSame(book, getBookByTitle, () -> "Book inserted and extracted (with same title) are not the same")
        );
    }
    
    @DisplayName("Test Find Operations")
    @Test
    void testFindOperationOnBook() {
        Book book = new Book();
        book.setTitle("ALITA - BATTLE ANGEL");
        book.setPrice(12.5F);
        book.setDescription("This is a short description of this test book");
        book.setIsbn("1-808-bnh-35");
        book.setNbOfPage(350);
        book.setIllustrations(false);

        tx.begin();
        em.persist(book);
        tx.commit();
        
        @SuppressWarnings("unchecked")
		List<Book> books = em.createNamedQuery("Book.findAll").getResultList();
        assertAll(
        		() -> assertNotNull(
        				book.getId(), 
        				() -> "ID should not be null"),
        		() -> assertSame(
        				(Book) em.createNamedQuery("Book.findById").setParameter("id", book.getId()).getSingleResult(),
        				em.find(Book.class,  book.getId()),
        				() -> "different find methods should get the same result"),
        		() -> assertFalse(
        				book.getTitle().isEmpty(), 
        				() -> "Title should not be empty"),
        		() -> assertNotNull(
        				books, 
        				() -> "Result list should not be null")
        );
    }
}
