package dev.jefferson.userapi.dto;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {
	
	private UUID uuid;
	private	String	nome;
	private	String	cpf;
	private	String	endereco;
	private	String	email;
	private	String	telefone;
	private	LocalDate dataCadastro;
	

}
