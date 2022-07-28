package com.bolappAPI.demo.services;

import java.util.List;

import com.bolappAPI.demo.payloads.CategoryDTO;
import com.bolappAPI.demo.payloads.UserDTO;

public interface CategoryService {
	
	CategoryDTO Createcategory(CategoryDTO categorycategorydto); //DTO = Data Transfer Object
	CategoryDTO updatecategory(CategoryDTO categorycategorydto,Integer categoryID); //DTO = Data Transfer Object
	CategoryDTO getcategoryByID(Integer categoryID); //DTO = Data Transfer Object
	List<CategoryDTO> getAllcategory(); //DTO = Data Transfer Object
	void deletecategory(Integer categoryID); //DTO = Data Transfer Object


}
