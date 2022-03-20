package dev.jefferson.productapi.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.jefferson.productapi.model.Product;

public interface ProductRepository extends JpaRepository<Product, UUID>{

	@Query("select p from Product p order by p.nome")
	List<Product> findAllOrderByNome();

	List<Product> queryByNomeLike(String nome);

	Optional<Product> findByNomeIgnoreCaseOrIdentificacaoIgnoreCase(String nome, String identificacao);
	
	@Query("select p from Product p join Category c on p.category.id = c.id where c.id = :idCategory order by p.nome")
	List<Product> getProductsByCategory(UUID idCategory);

	Optional<Product> findByIdentificacaoIgnoreCase(String identificacao);

}
