package dev.jefferson.productapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import dev.jefferson.productapi.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ProductResponse {
	
	private UUID	id;	
	private	String	nome;
	private	BigDecimal	preco;
	private	String	descricao;
	private	String	identificacao;
	private	LocalDate	dataCadastro;
	private	Category category;

}
