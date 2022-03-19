package dev.jefferson.userapi.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dev.jefferson.userapi.dto.UserRequest;
import dev.jefferson.userapi.dto.UserResponse;
import dev.jefferson.userapi.exception.ResourceNotFoundException;
import dev.jefferson.userapi.model.User;
import dev.jefferson.userapi.repository.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

	private final UserRepository repository;
	
	
	public List<UserResponse> getAll(){
		List<User> users = repository.findAllOrderByNome();
		return users.stream()
				.map(User::convertToResponse)
				.collect(Collectors.toList());
	}
	
	
	public UserResponse findById(UUID uuid) {
		User user = findByIdMethod(uuid);
		return user.convertToResponse();
	}


	public UserResponse create(UserRequest dto) {
		verityIfResourceAlreadyExists(dto);
		User user = repository.save(dto.convertToEntity());
		return user.convertToResponse();
	}
	
	
	public void deleteById(UUID uuid) {
		User user = findByIdMethod(uuid);
		repository.delete(user);
	}
	
	
	public UserResponse findByCpf(String cpf) {
		User user = repository.findByCpf(cpf)
				.orElseThrow(() -> new ResourceNotFoundException("Entidade não encontrada com o CPF " + cpf));
		return user.convertToResponse();
	}
	
	
	public List<UserResponse> queryByNome(String nome){
		List<User> users = repository.queryByNomeLike(nome);
		return users.stream().map(User::convertToResponse)
				.collect(Collectors.toList());
	}
	
	
	private User findByIdMethod(UUID uuid) {
		return repository.findById(uuid)
				.orElseThrow(() -> new ResourceNotFoundException("Entidade não encontrada com o ID " + uuid));
	}
	
	
	private void verityIfResourceAlreadyExists(UserRequest dto) {
		//TODO: Regra para verificar se já é cadastrado
	}


}