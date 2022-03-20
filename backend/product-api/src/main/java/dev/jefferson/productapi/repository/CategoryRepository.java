package dev.jefferson.productapi.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.jefferson.productapi.model.Category;

public interface CategoryRepository extends JpaRepository<Category, UUID>{


	Optional<Category> findByNomeIgnoreCase(String nome);

	@Query("select c from Category c order by c.nome")
	List<Category> findAllOrderByNome();

	List<Category> queryByNomeLike(String nome);

}
