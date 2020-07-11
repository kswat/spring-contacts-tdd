package com.fictional.shop.controller;


import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fictional.shop.domain.CustomerContact;
import com.fictional.shop.service.ContactsManagementService;

@WebMvcTest(controllers = {ContactsManagementController.class})
class ContactsManagementControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ContactsManagementService contactsManagementService;
	
	@InjectMocks
	ContactsManagementController controller;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testAddContactHappyPath() throws Exception{
		// Create a contact
		CustomerContact mockContact = new CustomerContact();
		mockContact.setFirstName("Jenny");
		mockContact.setLastName("Johnson");
		doReturn(mockContact).when(contactsManagementService).add(any(CustomerContact.class));
		//invoke
		CustomerContact dummy = new CustomerContact();
//		MockHttpServletRequestBuilder requestBuilder =  MockMvcRequestBuilders.post("/addContact", dummy);
		
		mockMvc.perform(post("/addContact")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dummy)))
		.andExpect(status().isOk())
		.andExpect(forwardedUrl("success"));

		
	}
    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
	@Test
	void testAddContactFailPath() throws Exception{

		doReturn(null).when(contactsManagementService).add(any(CustomerContact.class));
		//invoke
		CustomerContact dummy = new CustomerContact();
		
		mockMvc.perform(post("/addContact")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dummy)))
		.andDo(print())
		.andExpect(status().isOk()) //use 302 if redirect is used
		.andExpect(forwardedUrl("failure"));

		
	}
}
