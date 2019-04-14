

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import model.Book;

public class App 
{        

    public static void main( String[] args )
    {
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("workshopJavaEE01");
    	EntityManager em = emf.createEntityManager();
    	EntityTransaction tx = em.getTransaction();

        int entityElementsNumber = 100;
        int batchsize = 30;

        
        try {
            long startTime = System.currentTimeMillis();
            tx.begin();
            for (int i = 0; i < entityElementsNumber; i++) {
                if(i > 0 && i % batchsize == 0) {
                    tx.commit();
                    tx.begin();
                    em.clear();
                }

                em.persist(new Book("Title " + i, 12.5F, "Description " + i, "ISBN" + i, 100 + i*5, (i%3==0)?false:true));
            }
            tx.commit();
            System.out.printf("Operations took %s ms", String.valueOf(System.currentTimeMillis() - startTime));
            
        } catch (RuntimeException e) {
            if(tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
            emf.close();
        }
    }
}
