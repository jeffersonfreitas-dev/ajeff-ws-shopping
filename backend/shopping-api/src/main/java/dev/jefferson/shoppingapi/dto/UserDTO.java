package dev.jefferson.shoppingapi.dto;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	private UUID uuid;
	private	String	nome;
	private	String	cpf;
	private	String	endereco;
	private	String	email;
	private	String	telefone;
	private	LocalDate dataCadastro;
}
