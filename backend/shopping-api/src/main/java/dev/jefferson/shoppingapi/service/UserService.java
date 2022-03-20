package dev.jefferson.shoppingapi.service;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import dev.jefferson.shoppingapi.dto.UserDTO;
import dev.jefferson.shoppingapi.exception.ResourceNotFoundException;

@Service
public class UserService {
	
	private final static String URL_SERVICO = "http://localhost:8080/api/v1/users/";
	
	public UserDTO getUserById(UUID uuid) {
		try {
			RestTemplate template = new RestTemplate();
			String url = URL_SERVICO + uuid;
			ResponseEntity<UserDTO> result = template.getForEntity(url, UserDTO.class);
			return result.getBody();
		} catch (HttpClientErrorException.NotFound e) {
			throw new ResourceNotFoundException("Usuário não localizado com o id " + uuid);
		} catch (ResourceAccessException e) {
			throw new ResourceNotAvailableEException("Serviço de usuários não disponível");
		}

	}

}
