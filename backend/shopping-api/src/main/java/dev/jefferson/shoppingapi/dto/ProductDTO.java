package dev.jefferson.shoppingapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

	private UUID	id;	
	private	String	nome;
	private	BigDecimal	preco;
	private	String	descricao;
	private	String	identificacao;
	private	LocalDate	dataCadastro;
	private	CategoryDTO category;
}
