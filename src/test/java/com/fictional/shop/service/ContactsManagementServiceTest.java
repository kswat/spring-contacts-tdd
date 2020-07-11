package com.fictional.shop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.fictional.shop.data.repos.CustomerContactRepository;
import com.fictional.shop.domain.CustomerContact;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
class ContactsManagementServiceTest {

	@Mock
	CustomerContactRepository contactRepository;
	
	@InjectMocks
	ContactsManagementService contactsManagementService;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void testAddContactHappyPath() {
		// Create a contact
		CustomerContact mockContact = new CustomerContact();
		mockContact.setFirstName("Jenny");
		mockContact.setLastName("Johnson");
		
		doReturn(mockContact).when(contactRepository).save(any(CustomerContact.class));
		//invoke
		CustomerContact createdContact = contactsManagementService.add(mockContact);
		
		//verify
		assertNotNull(createdContact);
		assertNotNull(createdContact.getId());
		assertEquals("Jenny", createdContact.getFirstName());
		
	}

}
