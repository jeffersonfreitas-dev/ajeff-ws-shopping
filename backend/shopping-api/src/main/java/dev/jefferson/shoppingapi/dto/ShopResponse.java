package dev.jefferson.shoppingapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopResponse {

	private UUID id;
	private UUID usuario;
	private BigDecimal total;
	private LocalDate data;
	private List<ItemResponse> items;
	
	public ShopResponse(UUID id, UUID usuario, BigDecimal total, LocalDate data) {
		this.id = id;
		this.usuario = usuario;
		this.total = total;
		this.data = data;
	}
	
	
}
