package com.ta.dao;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ta.models.NewsLetter;

@Repository
public interface NewsLetterRepository extends MongoRepository<NewsLetter, String> {

	Optional<NewsLetter> findByEmail(String email);

}
