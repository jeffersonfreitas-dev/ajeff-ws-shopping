package dev.jefferson.productapi.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryResponse {
	
	private UUID	id;	
	private	String	nome;
}
