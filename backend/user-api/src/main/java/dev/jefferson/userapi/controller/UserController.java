package dev.jefferson.userapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	

}
