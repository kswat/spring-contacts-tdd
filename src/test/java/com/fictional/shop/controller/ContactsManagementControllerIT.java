package com.fictional.shop.controller;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.fictional.shop.domain.CustomerContact;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class ContactsManagementControllerIT {

	@Autowired
	ContactsManagementController controller;
	
//	@BeforeEach
//	void setUp() throws Exception {
//	}

	@Test
	void testAddContactHappyPath() {
		// Create a contact
		CustomerContact aContact = new CustomerContact();
		aContact.setFirstName("Jenny");
		aContact.setLastName("Johnson");
		
		//invoke		
		String response = controller.processAddContactSubmit(aContact);
		//verify
		assertNotNull(response);
		
		Assertions.assertEquals(response, "success");
		
	}
	
	@Test
	void testAddContactMissingFirstName() {
		// Create a contact
		CustomerContact aContact = new CustomerContact();		
		
		//invoke		
		String response = controller.processAddContactSubmit(aContact);
		//verify
		assertNotNull(response);
		
		Assertions.assertEquals(response, "failure");
		
	}
}
