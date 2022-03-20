package dev.jefferson.shoppingapi.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dev.jefferson.shoppingapi.dto.ShopRequest;
import dev.jefferson.shoppingapi.dto.ShopResponse;
import dev.jefferson.shoppingapi.exception.ResourceNotFoundException;
import dev.jefferson.shoppingapi.model.Shop;
import dev.jefferson.shoppingapi.repository.ShopRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ShopService {


	private final ShopRepository repository;
	
	
	public List<ShopResponse> getAll(){
		List<Shop> products = repository.findAllOrderByData();
		return products.stream()
				.map(Shop::convertToResponse)
				.collect(Collectors.toList());
	}
	
	
	public List<ShopResponse> getByUser(UUID user){
		List<Shop> shops = repository.findAllByUsuario(user);
		return shops.stream().map(Shop::convertToResponse)
				.collect(Collectors.toList());
	}
	
	
	public ShopResponse findById(UUID uuid) {
		Shop shop = findByIdMethod(uuid);
		return shop.convertToResponse();
	}


	public ShopResponse create(ShopRequest dto) {
		Shop shop = dto.convertToEntity();
		shop = repository.save(shop);
		return shop.convertToResponse();
	}
	
	
	public void deleteById(UUID uuid) {
		Shop shop = findByIdMethod(uuid);
		repository.delete(shop);
	}
	
	
	private Shop findByIdMethod(UUID uuid) {
		return repository.findById(uuid)
				.orElseThrow(() -> new ResourceNotFoundException("Compra não encontrada com o ID " + uuid));
	}
	
}
