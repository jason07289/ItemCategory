package com.jason07289.controller;

import java.util.Map;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jason07289.dto.CategoryDto;
import com.jason07289.service.CategoryService;
import com.jason07289.service.ResponseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins="{*}", maxAge=6000)
@RestController
@Api(value="Category", description="jaehyuk Item Category PJT")
@EnableAutoConfiguration
@RequiredArgsConstructor
public class CategoryController {
	
	private final ResponseService responseService;
	private final CategoryService categoryService;
	
	@ApiOperation("카테고리등록.")
	@PostMapping(value = "/category")
	public ResponseEntity<Map<String, Object>> saveCategory(@RequestBody CategoryDto categoryDto) {
		
		try {
			return responseService.handleSuccess(categoryService.saveCategory(categoryDto)+" 카테고리 등록 완료.");
		}catch (Exception e) {
			return responseService.handleFail(e.toString(), HttpStatus.BAD_REQUEST); 
		}
	}
	
	@ApiOperation("Id로 카테고리조회. dto는 Map구조로 반환")
	@GetMapping(value = "/category/{id}")
	public ResponseEntity<Map<String, Object>> getCategoryByCategoryId(@PathVariable Long id) {
		
		try {
			return responseService.handleSuccess(categoryService.getCategoryByCategoryId(id));
		}catch (Exception e) {
			return responseService.handleFail(e.toString(), HttpStatus.BAD_REQUEST); 
		}
	}
	
	@ApiOperation("전체 카테고리조회. dto는 Map구조로 반환")
	@GetMapping(value = "/category")
	public ResponseEntity<Map<String, Object>> getCategoryAll() {
		
		try {
			return responseService.handleSuccess(null);
		}catch (Exception e) {
			return responseService.handleFail(e.toString(), HttpStatus.BAD_REQUEST); 
		}
	}
	
	@ApiOperation("Id로 카테고리삭제. Map구조로 반환")
	@DeleteMapping(value = "/category/{id}")
	public ResponseEntity<Map<String, Object>> deleteCategory(@PathVariable Long id) {
		
		try {
			categoryService.deleteCategory(id);
			return responseService.handleSuccess(id+" 카테고리 삭제 완료.");
		}catch (Exception e) {
			return responseService.handleFail(e.toString(), HttpStatus.BAD_REQUEST); 
		}
	}
	
	@ApiOperation("카테고리업데이트.")
	@PutMapping(value = "/category/{id}")
	public ResponseEntity<Map<String, Object>> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
		
		try {
			return responseService.handleSuccess(categoryService.updateCategory(id, categoryDto)+" 카테고리 업데이트 완료.");
		}catch (Exception e) {
			return responseService.handleFail(e.toString(), HttpStatus.BAD_REQUEST); 
		}
	}
}
