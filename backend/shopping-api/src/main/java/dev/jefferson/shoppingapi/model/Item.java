package dev.jefferson.shoppingapi.model;

import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.persistence.PrePersist;

import dev.jefferson.shoppingapi.dto.ItemResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

	private String produto;
	
	private BigDecimal preco;
	
	@PrePersist
	public void setValorZerado() {
		if(this.preco == null) {
			this.preco = BigDecimal.ZERO;
		}
	}
	
	public ItemResponse convertToResponse() {
		return new ItemResponse(this.produto, this.preco);
	}
	
}
