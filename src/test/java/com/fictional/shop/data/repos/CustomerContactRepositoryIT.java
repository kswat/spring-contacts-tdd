package com.fictional.shop.data.repos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

//import static org.junit.Assert.assertThat;
import com.fictional.shop.domain.CustomerContact;
//@ExtendWith(SpringBootTest.class)
@DataJpaTest
class CustomerContactRepositoryIT {

	private static final String TEST_EMAIL = "joe@myemail.com";

	@Autowired
    private TestEntityManager entityManager;

	@Autowired
	private CustomerContactRepository customerContactRepository;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testFindByEmail() {
		// setup data scenario
		CustomerContact aNewContact = new CustomerContact();
		aNewContact.setEmail(TEST_EMAIL);
        
		// save test data
		entityManager.persist(aNewContact);

        // Find an inserted record
        CustomerContact foundContact = customerContactRepository.findByEmail(TEST_EMAIL);
        
        Assertions.assertEquals(foundContact.getEmail(), (TEST_EMAIL));
	}

}
