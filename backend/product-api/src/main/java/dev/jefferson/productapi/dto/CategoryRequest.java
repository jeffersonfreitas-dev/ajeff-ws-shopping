package dev.jefferson.productapi.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import dev.jefferson.productapi.model.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class CategoryRequest {
	
	@NotBlank(message = "Informe um nome para a categoria")
	@Size(max = 100, message = "O campo nome deverá conter no máximo 100 caracteres")	
	private	String	nome;

	public CategoryRequest(String nome) {
		this.nome = nome;
	}
	
	
	public Category convertToEntity() {
		return new Category(this.nome);
	}
	

}
