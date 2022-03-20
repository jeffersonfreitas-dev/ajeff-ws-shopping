package dev.jefferson.shoppingapi.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShopReport {

	private BigDecimal total;
	private BigDecimal mean;
}
