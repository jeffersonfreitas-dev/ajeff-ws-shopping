package dev.jefferson.userapi.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.jefferson.userapi.dto.UserRequest;
import dev.jefferson.userapi.dto.UserResponse;
import dev.jefferson.userapi.service.UserService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {
	
	private final UserService service;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<UserResponse> getUsers(){
		return service.getAll();
	}

	
	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public UserResponse findById(@PathVariable UUID id){
		return service.findById(id);
	}
	
	
	@GetMapping("/cpf/{cpf}")
	@ResponseStatus(HttpStatus.OK)
	public UserResponse findByCpf(@PathVariable String cpf){
		return service.findByCpf(cpf);
	}
	
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable UUID id){
		service.deleteById(id);
	}
	
	
	@GetMapping("/search")
	@ResponseStatus(HttpStatus.OK)
	public List<UserResponse> queryByName(@RequestParam(name = "nome", required = true) String nome){
		return service.queryByNome(nome);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserResponse create(@RequestBody @Valid UserRequest dto) {
		return service.create(dto);
	}
	
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public UserResponse update (@PathVariable UUID id, @RequestBody @Valid UserRequest dto) {
		return service.update(id, dto);
	}
}
