package listener;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import model.Customer;

public class ValidateBirthDateListener {

	@PrePersist
	@PreUpdate
	private void validate(Customer customer) {
		if (customer.getDateOfBirth().getTime() > new Date().getTime()) {
			throw new IllegalArgumentException("Invalid date of birth");
		}
	}
}
