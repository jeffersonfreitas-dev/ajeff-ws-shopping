package dev.jefferson.shoppingapi.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import dev.jefferson.shoppingapi.dto.ShopResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "shopping")
@Data @NoArgsConstructor
public class Shop {
	
	@Id
	@Type(type = "uuid-char")
	private UUID id;
	
	@Column(name = "usuario",  nullable = false, length = 100)	
	private UUID usuario;
	private BigDecimal total;
	private LocalDate data;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "item", joinColumns = @JoinColumn(name = "id_shop"))
	private List<Item> items;
	
	@PrePersist
	private void setDataShop() {
		this.data = LocalDate.now();
	}
	
	
	public Shop(UUID usuario) {
		this.id = UUID.randomUUID();
		this.usuario = usuario;
	}
	
	
	public ShopResponse convertToResponse() {
		ShopResponse response = new ShopResponse(this.id, this.usuario, this.total, this.data);
		response.setItems(this.items.stream().map(Item::convertToResponse).collect(Collectors.toList()));
		return response;
	}





}
