package dev.jefferson.shoppingapi.dto;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import dev.jefferson.shoppingapi.model.Shop;
import lombok.Data;

@Data
public class ShopRequest {

	@NotNull(message = "Informe o código do usuário")
	private UUID usuario;
	
//	@Size(min = 1, message = "Informe pelo menos um item na compra")
	private List<ItemRequest> items;
	
	
	public Shop convertToEntity() {
		Shop shop =  new Shop(this.usuario);
		shop.setItems(items.stream().map(ItemRequest::convertToEntity).collect(Collectors.toList()));
		return shop;
	}
}
