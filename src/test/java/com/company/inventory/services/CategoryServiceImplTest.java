package com.company.inventory.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.company.inventory.dao.ICategoryDao;
import com.company.inventory.model.Category;
import com.company.inventory.response.CategoryResponseRest;

class CategoryServiceImplTest {

	@InjectMocks
	CategoryServiceImpl service;
	
	@Mock
	ICategoryDao categoryDao;
	
	List<Category> list = new ArrayList<Category>();
	
	@BeforeEach
	public void init() {
		
		MockitoAnnotations.openMocks(this);
		this.chargeCategory();
		
	}
	
	@Test
	public void searchTest() {
		
		when(categoryDao.findAll()).thenReturn(list);
		
		ResponseEntity<CategoryResponseRest> response = service.search();
		
		assertEquals(3, response.getBody().getCategoryResponse().getCategory().size());
		
		verify(categoryDao, times(1)).findAll();
		
	}
	
	
	public void chargeCategory() {
		
		Category cat1 = new Category(Long.valueOf(1), "Abarrotes", "Distintos tipos de abarrores");
		Category cat2 = new Category(Long.valueOf(1), "Lacteos", "variedad de lacteos");
		Category cat3 = new Category(Long.valueOf(1), "Carnes blancas", "distintas carnes blancas");

		list.add(cat1);
		list.add(cat2);
		list.add(cat3);

	}
	
	
	

}
