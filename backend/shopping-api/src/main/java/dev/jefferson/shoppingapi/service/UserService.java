package dev.jefferson.shoppingapi.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import dev.jefferson.shoppingapi.dto.UserDTO;
import dev.jefferson.shoppingapi.exception.ResourceNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private Environment env;
	
	public UserDTO getUserById(UUID uuid) {
		String url = env.getProperty("shopping-api.url.servico.usuario") + uuid;

		try {
			RestTemplate template = new RestTemplate();
			ResponseEntity<UserDTO> result = template.getForEntity(url, UserDTO.class);
			return result.getBody();
		} catch (HttpClientErrorException.NotFound e) {
			throw new ResourceNotFoundException("Usuário não localizado com o id " + uuid);
		} catch (ResourceAccessException e) {
			throw new ResourceNotAvailableEException("Serviço de usuários não disponível");
		}

	}

}
