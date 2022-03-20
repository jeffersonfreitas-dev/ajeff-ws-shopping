package dev.jefferson.productapi.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import dev.jefferson.productapi.model.Product;
import lombok.Data;

@Data
public class ProductRequest {
	
	@NotBlank(message = "Informe um nome para o produto")
	@Size(max = 100, message = "O campo nome deverá conter no máximo 100 caracteres")	
	private	String	nome;
	
	private	BigDecimal	preco;
	
	@NotBlank(message = "Informe uma descrição para o produto")
	@Size(max = 100, message = "O campo descrição deverá conter no máximo 100 caracteres")
	private	String	descricao;
	
	@NotBlank(message = "Informe uma identificação para o produto")
	@Size(max = 100, message = "O campo identificacao deverá conter no máximo 100 caracteres")
	private	String	identificacao;

	private	CategoryRequest categoryRequest;
	
	
	public Product convertToEntity() {
		Product product =  new Product(
							this.nome, this.preco, this.descricao, this.identificacao);
		if(this.categoryRequest != null) {
			product.setCategory(categoryRequest.convertToEntity());
		}
		return product;
	}
	

}
