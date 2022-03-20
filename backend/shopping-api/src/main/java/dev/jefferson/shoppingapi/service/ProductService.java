package dev.jefferson.shoppingapi.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import dev.jefferson.shoppingapi.dto.ProductDTO;
import dev.jefferson.shoppingapi.exception.ResourceNotFoundException;

@Service
public class ProductService {
	
	private static final String URL_SERVICO = "http://localhost:8081/api/v1/products/";
	
	public ProductDTO getProductByIdentifier(String id) {
		try {
			RestTemplate template = new RestTemplate();
			String url = URL_SERVICO + "id/" + id;
			ResponseEntity<ProductDTO> result = template.getForEntity(url, ProductDTO.class);
			return result.getBody();
		} catch (HttpClientErrorException.NotFound e) {
			throw new ResourceNotFoundException("Produto não localizado com o codigo de identificação " + id);
		}catch (ResourceAccessException e) {
			throw new ResourceNotAvailableEException("Serviço de produtos não disponível");
		}
	}

}
