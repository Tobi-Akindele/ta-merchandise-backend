package com.ta.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ta.dao.ContactRepository;
import com.ta.models.Contact;
import com.ta.services.ContactService;

@Service
public class IContactService implements ContactService {
	
	@Autowired
	private ContactRepository contactRepository;

	@Override
	public Contact createContact(Contact contact) {
		contact.setCreateAt(new Date());
		return contactRepository.save(contact);
	}

	@Override
	public List<Contact> getAllContactUs() {
		return contactRepository.findAll();
	}

}
