package dev.jefferson.productapi.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import dev.jefferson.productapi.dto.CategoryRequest;
import dev.jefferson.productapi.dto.CategoryResponse;
import dev.jefferson.productapi.exception.ResourceAlreadyRegisteredException;
import dev.jefferson.productapi.exception.ResourceNotFoundException;
import dev.jefferson.productapi.model.Category;
import dev.jefferson.productapi.repository.CategoryRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryService {


	private final CategoryRepository repository;
	
	
	public List<CategoryResponse> getAll(){
		List<Category> categories = repository.findAllOrderByNome();
		return categories.stream()
				.map(Category::convertToResponse)
				.collect(Collectors.toList());
	}
	
	
	public CategoryResponse findById(UUID uuid) {
		Category category = findByIdMethod(uuid);
		return category.convertToResponse();
	}


	public CategoryResponse create(CategoryRequest dto) {
		Category category = dto.convertToEntity();
		verifyIfResourceAlreadyExists(category);
		category = repository.save(category);
		return category.convertToResponse();
	}
	
	
	public void deleteById(UUID uuid) {
		Category product = findByIdMethod(uuid);
		repository.delete(product);
	}
	
	
	public List<CategoryResponse> queryByNome(String nome){
		List<Category> products = repository.queryByNomeLike(nome);
		return products.stream().map(Category::convertToResponse)
				.collect(Collectors.toList());
	}
	
	
	public CategoryResponse update(UUID id, @Valid CategoryRequest dto) {
		Category category = findByIdMethod(id);
		category.setNome(dto.getNome());
		verifyIfResourceAlreadyExists(category);
		category = repository.save(category);
		return category.convertToResponse();
	}
	
	
	private Category findByIdMethod(UUID uuid) {
		return repository.findById(uuid)
				.orElseThrow(() -> new ResourceNotFoundException("Entidade não encontrada com o ID " + uuid));
	}
	
	
	private void verifyIfResourceAlreadyExists(Category category) {
		Optional<Category> optCat = repository.findByNomeIgnoreCase(category.getNome());
		if(optCat.isPresent() && !optCat.get().equals(category)) {
			throw new ResourceAlreadyRegisteredException("Já existe uma categoria com este nome cadastrada");
		}
	}
}
