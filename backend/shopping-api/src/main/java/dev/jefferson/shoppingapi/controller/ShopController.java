package dev.jefferson.shoppingapi.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.jefferson.shoppingapi.dto.ShopRequest;
import dev.jefferson.shoppingapi.dto.ShopResponse;
import dev.jefferson.shoppingapi.service.ShopService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/shops")
@AllArgsConstructor
public class ShopController {

	
	private final ShopService service;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ShopResponse> getShops(){
		return service.getAll();
	}

	
	@GetMapping("/search")
	@ResponseStatus(HttpStatus.OK)
	public List<ShopResponse> getShopsByFilter(
			@RequestParam(name = "inicio", required = true) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate inicio,
			@RequestParam(name = "fim", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fim,
			@RequestParam(name = "minimo", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") BigDecimal minimo){
		return service.getShopsByFilter(inicio, fim, minimo);
	}
	
	
	@GetMapping("/usuario/{id}")
	@ResponseStatus(HttpStatus.OK)
	public List<ShopResponse> getShops(@PathVariable UUID id){
		return service.getByUser(id);
	}

	
	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public ShopResponse findById(@PathVariable UUID id){
		return service.findById(id);
	}
	
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable UUID id){
		service.deleteById(id);
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ShopResponse create(@RequestBody @Valid ShopRequest dto) {
		return service.create(dto);
	}
	
}
