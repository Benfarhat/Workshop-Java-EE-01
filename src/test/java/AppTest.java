import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

public class AppTest {
    
    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static EntityTransaction tx;
    
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        emf = Persistence.createEntityManagerFactory("workshopJavaEE01");
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
    
    @Test
    @DisplayName("Create Book")
    void createBook() {
        Book book = new Book();
        book.setTitle("This is the test title");
        book.setPrice(12.5F);
        book.setDescription("This is a short description of this test book");
        book.setIsbn("1-808-bnh-35");
        book.setNbOfPage(350);
        book.setIllustrations(false);

        tx.begin();
        em.persist(book);
        tx.commit();
  
        assertNotNull(book.getId(), "L'ID ne doit pas être null");
        
        List<Book> books = em.createNamedQuery("findAllBooks").getResultList();
        
        assertNotNull(books);
    }
}
