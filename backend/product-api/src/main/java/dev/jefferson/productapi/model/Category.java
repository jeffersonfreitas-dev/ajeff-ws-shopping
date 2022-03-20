package dev.jefferson.productapi.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import dev.jefferson.productapi.dto.CategoryResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categories")
@Data @NoArgsConstructor
@AllArgsConstructor
public class Category {
	
	@Id
	@Type(type = "uuid-char")
	private UUID	id;	
	
	@Column(name = "nome", unique = true,  nullable = false, length = 100)	
	private	String	nome;

	public Category(String nome) {
		this.id = UUID.randomUUID();
		this.nome = nome;
	}
	
	public CategoryResponse convertToResponse() {
		return new CategoryResponse(this.id, this.nome);
	}
	

}
