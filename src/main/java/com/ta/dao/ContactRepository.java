package com.ta.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ta.models.Contact;

@Repository
public interface ContactRepository extends MongoRepository<Contact, String> {

}
