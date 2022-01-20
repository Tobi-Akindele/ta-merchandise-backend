package com.ta.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ta.exceptions.BadRequestException;
import com.ta.models.Contact;
import com.ta.services.ContactService;
import com.ta.utils.Utils;

@RestController
@RequestMapping("/api")
public class ContactController {

	@Autowired
	private ContactService contactService;
	
	
	@PostMapping("/contactus")
	public ResponseEntity<?> createContactUs(@RequestBody Contact contact) {

		if (contact == null || Utils.isEmptyString(contact.getName()) 
				|| Utils.isEmptyString(contact.getEmail())
				|| Utils.isEmptyString(contact.getMessage()))
			throw new BadRequestException("400", "Incomplete request data");
		
		if(contact.getMessage().length() > 200)
			throw new BadRequestException("400", "Only 200 character allowed");

		contact = contactService.createContact(contact);
		return ResponseEntity.ok(contact);
	}
	
	@GetMapping("/contactus")
	public ResponseEntity<?> getContactus() {
		
		List<Contact> contactusList = contactService.getAllContactUs();
		return ResponseEntity.ok(contactusList);
	}
}
