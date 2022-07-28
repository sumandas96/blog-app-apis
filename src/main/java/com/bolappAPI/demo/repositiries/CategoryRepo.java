package com.bolappAPI.demo.repositiries;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bolappAPI.demo.models.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
