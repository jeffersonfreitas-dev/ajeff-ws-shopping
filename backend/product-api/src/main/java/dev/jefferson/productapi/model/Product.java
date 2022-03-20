package dev.jefferson.productapi.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import dev.jefferson.productapi.dto.ProductResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data @NoArgsConstructor
public class Product {
	
	@Id
	@Type(type = "uuid-char")
	private UUID	id;	
	
	@Column(name = "nome", unique = true,  nullable = false, length = 100)	
	private	String	nome;
	
	private	BigDecimal	preco;
	
	@Column(name = "descricao",  nullable = false, length = 100)
	private	String	descricao;
	
	@Column(name = "identificacao", unique = true,  nullable = false, length = 100)
	private	String	identificacao;
	
	@Column(name = "cadastro")
	private	LocalDate	dataCadastro;
	
	@ManyToOne
	@JoinColumn(name="id_categoria")
	private	Category category;

	
	@PrePersist
	private void setDataCadastro() {
		this.dataCadastro = LocalDate.now();
	}


	public Product(String nome, BigDecimal preco, String descricao, String identificacao, Category category) {
		this.id = UUID.randomUUID();
		this.nome = nome;
		this.preco = preco;
		this.descricao = descricao;
		this.identificacao = identificacao;
		this.category = category;
	}

	public Product(String nome, BigDecimal preco, String descricao, String identificacao) {
		this.id = UUID.randomUUID();
		this.nome = nome;
		this.preco = preco;
		this.descricao = descricao;
		this.identificacao = identificacao;
	}
	
	
	public ProductResponse convertToResponse() {
		return new ProductResponse(
				this.id, this.nome, this.preco, this.descricao, this.identificacao, this.dataCadastro, this.category);
	}
	
}
