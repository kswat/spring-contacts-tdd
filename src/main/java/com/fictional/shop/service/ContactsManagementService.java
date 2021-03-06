package com.fictional.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fictional.shop.data.repos.CustomerContactRepository;
import com.fictional.shop.domain.CustomerContact;


@Service
public class ContactsManagementService {

	@Autowired
	private CustomerContactRepository customerContactRepository;
	
	public CustomerContact add(CustomerContact aContact) {
		if(validate(aContact)) {
			CustomerContact newContact = customerContactRepository.save(aContact);		
			return newContact;	
		}
		return null;
	}

	private boolean validate(CustomerContact aContact) {
		return aContact.getFirstName()!= null && aContact.getLastName() != null;		
	}
	
	/*
	public CustomerContact addContactOccasion(CustomerContact aContact, ContactImportantOccasion anOccasion) {
		CustomerContact newContact = null;
		
		return newContact;	
	}
	*/
}
