package com.bolappAPI.demo.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bolappAPI.demo.exceptions.ResourceNotFoundException;
import com.bolappAPI.demo.models.Category;
import com.bolappAPI.demo.payloads.CategoryDTO;
import com.bolappAPI.demo.repositiries.CategoryRepo;
import com.bolappAPI.demo.services.CategoryService;

@Service
public class CategoryServiceimpl implements CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDTO Createcategory(CategoryDTO categorydto) {
		// TODO Auto-generated method stub
		Category category = this.dtoToCategory(categorydto);
		Category saveCategory = categoryRepo.save(category);
		
		return this.categoryTodto(saveCategory);
	}

	@Override
	public CategoryDTO updatecategory(CategoryDTO categorydto, Integer categoryID) {
		// TODO Auto-generated method stub
		Category category = categoryRepo.findById(categoryID)
				.orElseThrow(()-> new ResourceNotFoundException("category","id",categoryID));
		
		category.setCategoryTitle(categorydto.getCategoryTitle());
		category.setCategoryDescription(categorydto.getCategoryDescription());
		
		Category updatedCategory = categoryRepo.save(category);
		CategoryDTO categoryDTO1 = categoryTodto(updatedCategory);
		return categoryDTO1;
	}

	@Override
	public CategoryDTO getcategoryByID(Integer categoryID) {
		// TODO Auto-generated method stub
		Category category = categoryRepo.findById(categoryID)
				.orElseThrow(()-> new ResourceNotFoundException("category","id",categoryID));
		return this.categoryTodto(category);
	}

	@Override
	public List<CategoryDTO> getAllcategory() {
		// TODO Auto-generated method stub
		List<Category> catList = categoryRepo.findAll();
		List<CategoryDTO> cateDtosList = catList.stream().map((category) -> categoryTodto(category)).collect(Collectors.toList());
		
		return cateDtosList;
	}

	@Override
	public void deletecategory(Integer categoryID) {
		// TODO Auto-generated method stub
		Category category = categoryRepo.findById(categoryID)
				.orElseThrow(()-> new ResourceNotFoundException("category","id",categoryID));
		categoryRepo.delete(category);

	}
	
	private Category dtoToCategory(CategoryDTO categoryDTO) {
		Category category = this.modelMapper.map(categoryDTO, Category.class);
		return category;
	}
	
	private CategoryDTO categoryTodto(Category category) {
		CategoryDTO categorydto = this.modelMapper.map(category, CategoryDTO.class);
		return categorydto;
	}


}
