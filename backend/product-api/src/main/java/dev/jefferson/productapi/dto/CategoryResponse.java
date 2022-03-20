package dev.jefferson.productapi.dto;

import java.util.UUID;

import dev.jefferson.productapi.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryResponse {
	
	private UUID	id;	
	private	String	nome;
	
	public Category convertToEntity() {
		return new Category(this.id, this.nome);
	}
}
