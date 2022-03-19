package dev.jefferson.userapi.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import dev.jefferson.userapi.dto.UserRequest;
import dev.jefferson.userapi.dto.UserResponse;
import dev.jefferson.userapi.exception.ResourceAlreadyRegisteredException;
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
		User user = dto.convertToEntity();
		verifyIfResourceAlreadyExists(user);
		user = repository.save(user);
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
	
	
	public UserResponse update(UUID id, @Valid UserRequest dto) {
		User user = findByIdMethod(id);
		user.setNome(dto.getNome());
		user.setCpf(dto.getCpf());
		user.setEmail(dto.getEmail());
		user.setEndereco(dto.getEndereco());
		user.setTelefone(dto.getTelefone());
		verifyIfResourceAlreadyExists(user);
		user = repository.save(user);
		return user.convertToResponse();
	}
	
	
	private User findByIdMethod(UUID uuid) {
		return repository.findById(uuid)
				.orElseThrow(() -> new ResourceNotFoundException("Entidade não encontrada com o ID " + uuid));
	}
	
	
	private void verifyIfResourceAlreadyExists(User user) {
		Optional<User> optNome = repository.findByNomeIgnoreCase(user.getNome());
		if(optNome.isPresent() && !optNome.get().equals(user)) {
			throw new ResourceAlreadyRegisteredException("Já existe um usuário com este nome cadastrado");
		}
		
		Optional<User> optCpf = repository.findByCpf(user.getCpf());
		if(optCpf.isPresent() && !optCpf.get().equals(user)) {
			throw new ResourceAlreadyRegisteredException("Já existe um usuário com este CPF cadastrado");
		}
	}

}
