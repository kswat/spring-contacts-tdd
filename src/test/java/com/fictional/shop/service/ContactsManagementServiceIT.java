package com.fictional.shop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.fictional.shop.domain.CustomerContact;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
class ContactsManagementServiceIT {

	@Autowired
	ContactsManagementService contactsManagementService;
	
	@Test
	void testAddContactHappyPath() {
		// Create a contact
		CustomerContact aContact = new CustomerContact();
		aContact.setFirstName("Jenny");
		aContact.setLastName("Johnson");
		
		//invoke
		CustomerContact createdContact = contactsManagementService.add(aContact);
		
		//verify
		assertNotNull(createdContact);
		assertNotNull(createdContact.getId());
		assertEquals("Jenny", createdContact.getFirstName());
		
	}

}
