package dev.jefferson.shoppingapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import dev.jefferson.shoppingapi.dto.ProductDTO;
import dev.jefferson.shoppingapi.exception.ResourceNotFoundException;

@Service
public class ProductService {
	
	@Autowired
	private Environment env;
	
	public ProductDTO getProductByIdentifier(String id) {
		String url = env.getProperty("shopping-api.url.servico.produto") + "id/" + id;
		
		try {
			RestTemplate template = new RestTemplate();
			ResponseEntity<ProductDTO> result = template.getForEntity(url, ProductDTO.class);
			return result.getBody();
		} catch (HttpClientErrorException.NotFound e) {
			throw new ResourceNotFoundException("Produto não localizado com o codigo de identificação " + id);
		}catch (ResourceAccessException e) {
			throw new ResourceNotAvailableEException("Serviço de produtos não disponível");
		}
	}

}
