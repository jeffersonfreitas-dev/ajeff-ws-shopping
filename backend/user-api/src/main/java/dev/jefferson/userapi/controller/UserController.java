package dev.jefferson.userapi.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
	public UserResponse findById(UUID uuid){
		return service.findById(uuid);
	}
	
	
	@GetMapping("/cpf/{cpf}")
	@ResponseStatus(HttpStatus.OK)
	public UserResponse findByCpf(String cpf){
		return service.findByCpf(cpf);
	}
	
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(UUID uuid){
		service.deleteById(uuid);
	}
	
	
	@GetMapping("/search")
	public List<UserResponse> queryByName(@RequestParam(name = "nome", required = true) String nome){
		return service.queryByNome(nome);
	}
	
	
	

}
