package dev.jefferson.productapi.dto;

import java.math.BigDecimal;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

	@NotNull(message = "Informe o código da categoria do produto")
	private	UUID category;
	
	
	public Product convertToEntity() {
		return  new Product(
							this.nome, this.preco, this.descricao, this.identificacao);
	}
	

}
