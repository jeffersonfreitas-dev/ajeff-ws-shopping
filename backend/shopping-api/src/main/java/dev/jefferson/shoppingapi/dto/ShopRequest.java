package dev.jefferson.shoppingapi.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import dev.jefferson.shoppingapi.model.Shop;
import lombok.Data;

@Data
public class ShopRequest {

	@NotNull(message = "Informe o código do usuário")
	private UUID usuario;
	
	@Valid
	@Size(min = 1, message = "Informe pelo menos um item na compra")
	private List<ItemRequest> items;
	
	
	public Shop convertToEntity() {
		Shop shop =  new Shop(this.usuario);
		shop.setItems(items.stream().map(ItemRequest::convertToEntity).collect(Collectors.toList()));
		shop.setTotal(total().get());
		return shop;
	}
	
	private Optional<BigDecimal> total() {
		return this.items.stream().map(i -> i.getPreco()).reduce(BigDecimal::add);
	}
}
