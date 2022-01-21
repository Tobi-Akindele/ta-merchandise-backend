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
import com.ta.models.NewsLetter;
import com.ta.services.NewsLetterService;
import com.ta.utils.Utils;

@RestController
@RequestMapping("/api")
public class NewsLetterController {

	@Autowired
	private NewsLetterService newsLetterService;

	@PostMapping("/newsletter")
	public ResponseEntity<?> createNewsLetter(@RequestBody NewsLetter newsLetter) {

		if (newsLetter == null || Utils.isEmptyString(newsLetter.getEmail()))
			throw new BadRequestException("400", "Incomplete request data");
		
		NewsLetter newsLetterInDb = newsLetterService.getNewsLetterByEmail(newsLetter.getEmail());
		if(newsLetterInDb != null)
			return ResponseEntity.ok(newsLetterInDb);

		newsLetter = newsLetterService.createNewsLetter(newsLetter);
		return ResponseEntity.ok(newsLetter);
	}

	@GetMapping("/newsletter")
	public ResponseEntity<?> getNewsLetters() {

		List<NewsLetter> newsLetterList = newsLetterService.getAllNewsLetter();
		return ResponseEntity.ok(newsLetterList);
	}
}
