package com.ta.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ta.dao.NewsLetterRepository;
import com.ta.models.NewsLetter;
import com.ta.services.NewsLetterService;

@Service
public class INewsLetterService implements NewsLetterService {
	
	@Autowired
	private NewsLetterRepository newsLetterRepository;

	@Override
	public NewsLetter createNewsLetter(NewsLetter newsLetter) {
		newsLetter.setCreateAt(new Date());
		return newsLetterRepository.save(newsLetter);
	}

	@Override
	public List<NewsLetter> getAllNewsLetter() {
		return newsLetterRepository.findAll();
	}

	@Override
	public NewsLetter getNewsLetterByEmail(String email) {
		return newsLetterRepository.findByEmail(email).orElse(null);
	}

}
