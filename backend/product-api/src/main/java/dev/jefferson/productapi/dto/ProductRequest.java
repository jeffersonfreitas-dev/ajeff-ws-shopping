package dev.jefferson.productapi.dto;

import java.math.BigDecimal;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import dev.jefferson.productapi.model.Product;
import dev.jefferson.productapi.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductRequest {
	
	private final CategoryService categoryService;
	
	
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

	private	UUID idCategory;
	
	
	public Product convertToEntity() {
		Product product =  new Product(
							this.nome, this.preco, this.descricao, this.identificacao);
		if(this.idCategory != null) {
			CategoryResponse cat = categoryService.findById(idCategory);
			product.setCategory(cat.convertToEntity());
		}
		return product;
	}
	

}
