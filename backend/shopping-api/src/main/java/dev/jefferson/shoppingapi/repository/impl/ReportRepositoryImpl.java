package dev.jefferson.shoppingapi.repository.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dev.jefferson.shoppingapi.model.Shop;
import dev.jefferson.shoppingapi.repository.ReportRepository;

public class ReportRepositoryImpl implements ReportRepository{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	@SuppressWarnings("unchecked")
	public List<Shop> getShopByFilters(LocalDate inicio, LocalDate fim, BigDecimal minimo) {
		StringBuilder sb = new StringBuilder();
		sb.append("select s from Shop s where s.data >= : inicio ");
		if(fim != null) sb.append("and s.data <= :fim");
		if(minimo != null) sb.append("and s.total <= :minimo");
		
		Query query = manager.createQuery(sb.toString());
		query.setParameter("inicio", inicio);
		
		if(fim != null) query.setParameter("fim", fim);
		if(minimo != null) query.setParameter("minimo", minimo);
		return query.getResultList();
	}

}
