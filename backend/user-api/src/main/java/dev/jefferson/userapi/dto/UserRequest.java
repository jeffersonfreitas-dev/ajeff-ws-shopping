package dev.jefferson.userapi.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import dev.jefferson.userapi.model.User;
import lombok.Data;

@Data
public class UserRequest {

	@NotBlank(message = "Informe um nome para o usuário")
	@Size(max = 100, message = "O campo nome deverá conter no máximo 100 caracteres")
	private	String	nome;

	@NotBlank(message = "Informe o CPF do usuário")
	@Size(max = 15, message = "O campo CPF deverá conter no máximo 15 caracteres")
	private	String	cpf;
	
	@Size(max = 100, message = "O campo endereço deverá conter no máximo 100 caracteres")
	private	String	endereco;
	
	@Email(message = "Informe um e-mail válido")
	@Size(max = 100, message = "O campo e-email deverá conter no máximo 100 caracteres")
	private	String	email;

	@Size(max = 20, message = "O campo telefone deverá conter no máximo 20 caracteres")
	private	String	telefone;
	
	public User convertToEntity() {
		return new User(this.nome, this.cpf, this.endereco, this.email, this.telefone);
	}
	
	
}
