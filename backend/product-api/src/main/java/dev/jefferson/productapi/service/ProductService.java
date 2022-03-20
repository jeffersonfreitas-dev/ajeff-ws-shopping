package dev.jefferson.productapi.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import dev.jefferson.productapi.dto.CategoryResponse;
import dev.jefferson.productapi.dto.ProductRequest;
import dev.jefferson.productapi.dto.ProductResponse;
import dev.jefferson.productapi.exception.ResourceAlreadyRegisteredException;
import dev.jefferson.productapi.exception.ResourceNotFoundException;
import dev.jefferson.productapi.model.Product;
import dev.jefferson.productapi.repository.ProductRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {


	private final ProductRepository repository;
	private final CategoryService categoryService;
	
	
	public List<ProductResponse> getAll(){
		List<Product> products = repository.findAllOrderByNome();
		return products.stream()
				.map(Product::convertToResponse)
				.collect(Collectors.toList());
	}
	
	
	public List<ProductResponse> getProductByCategory(UUID idCategory){
		List<Product> products = repository.getProductsByCategory(idCategory);
		return products.stream().map(Product::convertToResponse)
				.collect(Collectors.toList());
	}
	
	
	public ProductResponse findById(UUID uuid) {
		Product product = findByIdMethod(uuid);
		return product.convertToResponse();
	}


	public ProductResponse create(ProductRequest dto) {
		Product product = dto.convertToEntity();
		verifyIfResourceAlreadyExists(product);
		CategoryResponse cat = categoryService.findById(dto.getCategory());
		product.setCategory(cat.convertToEntity());
		product = repository.save(product);
		return product.convertToResponse();
	}
	
	
	public void deleteById(UUID uuid) {
		Product product = findByIdMethod(uuid);
		repository.delete(product);
	}
	
	
	public List<ProductResponse> queryByNome(String nome){
		List<Product> products = repository.queryByNomeLike(nome);
		return products.stream().map(Product::convertToResponse)
				.collect(Collectors.toList());
	}
	
	
	public ProductResponse update(UUID id, @Valid ProductRequest dto) {
		Product product = findByIdMethod(id);
		product.setNome(dto.getNome());
		CategoryResponse cat = categoryService.findById(dto.getCategory());
		product.setCategory(cat.convertToEntity());		
		product.setDescricao(dto.getDescricao());
		product.setIdentificacao(dto.getIdentificacao());
		product.setPreco(dto.getPreco());
		verifyIfResourceAlreadyExists(product);
		product = repository.save(product);
		return product.convertToResponse();
	}
	
	
	public ProductResponse getProductByIdentification(String identificacao) {
		Product prod = repository.findByIdentificacaoIgnoreCase(identificacao)
				.orElseThrow(() -> new ResourceNotFoundException("Entidade não encontrada com a identificação " + identificacao));
		return prod.convertToResponse();
	}
	
	
	private Product findByIdMethod(UUID uuid) {
		return repository.findById(uuid)
				.orElseThrow(() -> new ResourceNotFoundException("Entidade não encontrada com o ID " + uuid));
	}
	
	
	private void verifyIfResourceAlreadyExists(Product product) {
		Optional<Product> optNome = repository.findByNomeIgnoreCaseOrIdentificacaoIgnoreCase(product.getNome(), product.getIdentificacao());
		if(optNome.isPresent() && !optNome.get().equals(product)) {
			throw new ResourceAlreadyRegisteredException("Já existe um produto com este nome e/ou identificação cadastrado");
		}
	}
}
