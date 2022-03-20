package dev.jefferson.productapi.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.jefferson.productapi.dto.CategoryRequest;
import dev.jefferson.productapi.dto.CategoryResponse;
import dev.jefferson.productapi.service.CategoryService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/categories")
@AllArgsConstructor
public class CategoryController {

	
	private final CategoryService service;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<CategoryResponse> getCategories(){
		return service.getAll();
	}

	
	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public CategoryResponse findById(@PathVariable UUID id){
		return service.findById(id);
	}
	
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable UUID id){
		service.deleteById(id);
	}
	
	
	@GetMapping("/search")
	@ResponseStatus(HttpStatus.OK)
	public List<CategoryResponse> queryByName(@RequestParam(name = "nome", required = true) String nome){
		return service.queryByNome(nome);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CategoryResponse create(@RequestBody @Valid CategoryRequest dto) {
		return service.create(dto);
	}
	
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public CategoryResponse update (@PathVariable UUID id, @RequestBody @Valid CategoryRequest dto) {
		return service.update(id, dto);
	}
}
