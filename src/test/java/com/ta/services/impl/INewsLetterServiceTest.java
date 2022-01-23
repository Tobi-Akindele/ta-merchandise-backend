package com.ta.services.impl;

import com.ta.TestUtils.ModelUtils;
import com.ta.dao.NewsLetterRepository;
import com.ta.models.NewsLetter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class INewsLetterServiceTest {
    @Mock
    NewsLetterRepository newsLetterRepository;
    @InjectMocks
    INewsLetterService iNewsLetterService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateNewsLetter() throws Exception {
    	when(newsLetterRepository.save(any())).thenReturn(ModelUtils.getNewsLetter());
        NewsLetter result = iNewsLetterService.createNewsLetter(new NewsLetter());
        Assert.assertNotNull(result);
    }

    @Test
    public void testGetAllNewsLetter() throws Exception {
    	List<NewsLetter> newsLetters = new ArrayList<>();
    	newsLetters.add(ModelUtils.getNewsLetter());
    	when(newsLetterRepository.findAll()).thenReturn(newsLetters);
        List<NewsLetter> result = iNewsLetterService.getAllNewsLetter();
        Assert.assertTrue(result.size() > 0);
    }

    @Test
    public void testGetNewsLetterByEmail() throws Exception {
    	Optional<NewsLetter> newsLetter = Optional.of(ModelUtils.getNewsLetter());
        when(newsLetterRepository.findByEmail(anyString())).thenReturn(newsLetter);

        NewsLetter result = iNewsLetterService.getNewsLetterByEmail("email");
        Assert.assertNotNull(result);
    }
}