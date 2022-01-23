package com.ta.services;

import java.util.List;

import com.ta.models.Contact;

public interface ContactService {

	public Contact createContact(Contact contact);

	public List<Contact> getAllContactUs();

}
