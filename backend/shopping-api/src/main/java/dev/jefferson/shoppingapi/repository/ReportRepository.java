package dev.jefferson.shoppingapi.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import dev.jefferson.shoppingapi.model.Shop;

public interface ReportRepository {
	
	public List<Shop> getShopByFilters(LocalDate inicio, LocalDate fim, BigDecimal minimo);

}
