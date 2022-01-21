package com.ta.services;

import java.util.List;

import com.ta.models.NewsLetter;

public interface NewsLetterService {

	public NewsLetter createNewsLetter(NewsLetter newsLetter);

	public List<NewsLetter> getAllNewsLetter();

	public NewsLetter getNewsLetterByEmail(String email);

}
