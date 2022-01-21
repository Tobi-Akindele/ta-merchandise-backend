package com.ta.services.impl;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ta.TestUtils.ModelUtils;
import com.ta.dao.ProductRepository;
import com.ta.models.Product;

public class IProductServiceTest {
    @Mock
    ProductRepository productRepository;
    @InjectMocks
    IProductService iProductService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateProduct() throws Exception {
    	when(productRepository.save(any())).thenReturn(ModelUtils.getProduct());
        Product result = iProductService.createProduct(new Product());
        Assert.assertNotNull(result);
    }

    @Test
    public void testGetById() throws Exception {
    	Optional<Product> product = Optional.of(ModelUtils.getProduct());
    	when(productRepository.findById(any())).thenReturn(product);
        Product result = iProductService.getById("productId");
        Assert.assertNotNull(result);
    }

    @Test
    public void testUpdateProduct() throws Exception {
    	when(productRepository.save(any())).thenReturn(ModelUtils.getProduct());
        Product result = iProductService.updateProduct(new Product(), new Product());
        Assert.assertNotNull(result);
    }

    @Test
    public void testDeleteProduct() throws Exception {
    	doNothing().when(productRepository).delete(any());
        iProductService.deleteProduct(new Product());
        assertTrue(true);
    }
}