package dev.jefferson.shoppingapi.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.jefferson.shoppingapi.model.Shop;

public interface ShopRepository extends JpaRepository<Shop, UUID>{

	@Query("select s from Shop s order by s.data")
	List<Shop> findAllOrderByData();

	List<Shop> findAllByUsuario(UUID user);

}
