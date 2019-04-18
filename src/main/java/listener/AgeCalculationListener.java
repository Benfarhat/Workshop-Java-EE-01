package listener;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

import model.Customer;

public class AgeCalculationListener {
	@PostLoad
	@PostPersist
	@PostUpdate
	public void calculcateAge(Customer customer) {
		if (customer.getDateOfBirth() == null) {
			customer.setAge(null);
			return;
		}
		
		Calendar birth = new GregorianCalendar();
		birth.setTime(customer.getDateOfBirth());
		Calendar now  = new GregorianCalendar();
		now.setTime(new Date());
		
		int adjust = 0;
		if (now.get(Calendar.DAY_OF_YEAR) - birth.get(Calendar.DAY_OF_YEAR) < 0) {
			adjust = -1;
		}
		customer.setAge(now.getActualMaximum(Calendar.YEAR) - birth.getActualMaximum(Calendar.YEAR) + adjust);
	}
	
}
