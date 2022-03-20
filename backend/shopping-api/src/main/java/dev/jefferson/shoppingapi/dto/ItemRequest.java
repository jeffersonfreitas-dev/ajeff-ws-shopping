package dev.jefferson.shoppingapi.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import dev.jefferson.shoppingapi.model.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequest {
	
	@NotBlank(message = "Informe o código de identificação do produto")
	private String produto;
	
	@NotNull(message = "Informe o valor do produto")
	private BigDecimal preco;
	
	
	public Item convertToEntity() {
		return new Item(this.produto, this.preco);
	}

}
