

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * App
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        int entityElementsNumber = 100;
        int batchsize = 30;
        
        @SuppressWarnings("rawtypes")
        Map<String, Comparable> properties = new HashMap<String, Comparable>();
        properties.put("eclipselink.jdbc.batch-writing", "JDBC");
        properties.put("eclipselink.jdbc.cache-statements", "true");
        properties.put("eclipselink.jdbc.batch-writing.size", String.valueOf(batchsize));
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("workshopJavaEE01", properties);
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
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
