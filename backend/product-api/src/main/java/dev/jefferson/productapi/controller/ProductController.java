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

import dev.jefferson.productapi.dto.ProductRequest;
import dev.jefferson.productapi.dto.ProductResponse;
import dev.jefferson.productapi.service.ProductService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController {

	
	private final ProductService service;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ProductResponse> getProducts(){
		return service.getAll();
	}

	
	@GetMapping("/category/{id}")
	@ResponseStatus(HttpStatus.OK)
	public List<ProductResponse> getProducts(@PathVariable UUID id){
		return service.getProductByCategory(id);
	}

	
	@GetMapping("/id/{identificacao}")
	@ResponseStatus(HttpStatus.OK)
	public ProductResponse getProductByIdentification(@PathVariable String identificacao){
		return service.getProductByIdentification(identificacao);
	}

	
	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public ProductResponse findById(@PathVariable UUID id){
		return service.findById(id);
	}
	
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable UUID id){
		service.deleteById(id);
	}
	
	
	@GetMapping("/search")
	@ResponseStatus(HttpStatus.OK)
	public List<ProductResponse> queryByName(@RequestParam(name = "nome", required = true) String nome){
		return service.queryByNome(nome);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProductResponse create(@RequestBody @Valid ProductRequest dto) {
		return service.create(dto);
	}
	
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public ProductResponse update (@PathVariable UUID id, @RequestBody @Valid ProductRequest dto) {
		return service.update(id, dto);
	}
}
