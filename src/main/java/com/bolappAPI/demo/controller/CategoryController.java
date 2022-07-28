package com.bolappAPI.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bolappAPI.demo.payloads.ApiResponse;
import com.bolappAPI.demo.payloads.CategoryDTO;
import com.bolappAPI.demo.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService catService;

	// @Valid is for validating all the fields present in the Category model

	@PostMapping("/")
	public ResponseEntity<CategoryDTO> createCat(@Valid @RequestBody CategoryDTO categoryDTO) {
		CategoryDTO createCategory = this.catService.Createcategory(categoryDTO);
		return new ResponseEntity<CategoryDTO>(createCategory, HttpStatus.CREATED);
	}

	@PutMapping("/{categoryID}")
	public ResponseEntity<CategoryDTO> updateCat(@Valid @RequestBody CategoryDTO categoryDTO,
			@PathVariable("categoryID") int catId) {
		CategoryDTO updateCategory = this.catService.updatecategory(categoryDTO, catId);
		return new ResponseEntity<CategoryDTO>(updateCategory, HttpStatus.OK);

	}

	@DeleteMapping("/{categoryID}")
	public ResponseEntity<?> delCat(@PathVariable("categoryID") int catId) {
		this.catService.deletecategory(catId);
		return new ResponseEntity(new ApiResponse("User Deleted Successfully", true), HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<List<CategoryDTO>> getAllCategories() {
		return ResponseEntity.ok(this.catService.getAllcategory());
	}

	@GetMapping("/{categoryID}")
	public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable("categoryID") int catId) {
		return ResponseEntity.ok(this.catService.getcategoryByID(catId));
	}

}
