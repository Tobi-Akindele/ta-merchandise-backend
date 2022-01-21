package com.ta.services.impl;

import com.ta.TestUtils.ModelUtils;
import com.ta.dao.ContactRepository;
import com.ta.models.Contact;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class IContactServiceTest {
    @Mock
    ContactRepository contactRepository;
    @InjectMocks
    IContactService iContactService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateContact() throws Exception {
    	when(contactRepository.save(any())).thenReturn(ModelUtils.getContact());
        Contact result = iContactService.createContact(new Contact());
        Assert.assertNotNull(result);
    }

    @Test
    public void testGetAllContactUs() throws Exception {
    	List<Contact> contacts = new ArrayList<>();
    	contacts.add(ModelUtils.getContact());
    	when(contactRepository.findAll()).thenReturn(contacts);
        List<Contact> result = iContactService.getAllContactUs();
        Assert.assertTrue(result.size() > 0);
    }
}