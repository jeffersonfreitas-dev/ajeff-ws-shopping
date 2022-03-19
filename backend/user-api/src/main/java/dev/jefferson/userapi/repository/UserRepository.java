package dev.jefferson.userapi.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.jefferson.userapi.model.User;

public interface UserRepository extends JpaRepository<User, UUID>{

	Optional<User> findByCpf(String cpf);
	
	List<User> queryByNomeLike(String nome);

	@Query("select u from User u order by u.nome")
	List<User> findAllOrderByNome();

	Optional<User> findByNomeIgnoreCase(String nome);

}
