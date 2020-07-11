package com.fictional.shop.data.repos;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.fictional.shop.domain.CustomerContact;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;


@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
   TransactionalTestExecutionListener.class,
    DbUnitTestExecutionListener.class })
@DatabaseSetup("classpath:test-datasets.xml")

public class CustomerContactRepositoryDbUnitTest {

	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
	private CustomerContactRepository customerContactRepository;
	
	@Test
    public void testFindByEmail() {
		
        // Find an inserted record
        CustomerContact foundContact = customerContactRepository.findByEmail("elaine@myemail.com");
        
        assertEquals(foundContact.getEmail(), ("elaine@myemail.com"));
    }
	
	@Test
    public void testFindSpecificContactByIdBypassReposClass() {
		
        // Find an inserted record
        CustomerContact foundContact = entityManager.find(CustomerContact.class, new Long("2"));
        
        assertEquals(foundContact.getEmail(), "elaine@myemail.com");
    }
}
