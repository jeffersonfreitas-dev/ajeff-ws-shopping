package dev.jefferson.shoppingapi.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dev.jefferson.shoppingapi.dto.ItemRequest;
import dev.jefferson.shoppingapi.dto.ProductDTO;
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
	private final UserService userService;
	private final ProductService productService;
	
	
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
		userService.getUserById(dto.getUsuario());
		validarProdutosDaCompra(dto);
		Shop shop = dto.convertToEntity();
		shop = repository.save(shop);
		return shop.convertToResponse();
	}
	

	public void deleteById(UUID uuid) {
		Shop shop = findByIdMethod(uuid);
		repository.delete(shop);
	}
	
	
	public List<ShopResponse> getShopsByFilter(LocalDate inicio, LocalDate fim, BigDecimal minimo){
		List<Shop> shops = repository.getShopByFilters(inicio, fim, minimo);
		return shops.stream().map(Shop::convertToResponse).collect(Collectors.toList());
	}
	
	
	
	private Shop findByIdMethod(UUID uuid) {
		return repository.findById(uuid)
				.orElseThrow(() -> new ResourceNotFoundException("Compra não encontrada com o ID " + uuid));
	}
	
	
	private void validarProdutosDaCompra(ShopRequest dto) {
		for(ItemRequest i : dto.getItems()) {
			ProductDTO prod = productService.getProductByIdentifier(i.getProduto());
			if(prod == null) {
				throw new ResourceNotFoundException("Produto não localizado com a identificação " + i.getProduto());
			}
		}
		
	}
	
}
