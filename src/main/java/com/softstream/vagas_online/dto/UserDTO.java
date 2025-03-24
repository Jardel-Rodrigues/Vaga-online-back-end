package com.softstream.vagas_online.dto;

import java.util.HashSet;
import java.util.Set;

import com.softstream.vagas_online.entities.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	
	private Long id;
	
	@NotBlank(message = "Campo obrigatório")
	private String nome;

	@Email(message = "Favor informar um email válido")
	@NotBlank(message = "Campo obrigatório")
	private String email;
	
	@Size(min = 6, max = 8)
	@NotBlank(message = "Campo obrigatório")
	private String password;
	
	@NotBlank(message = "Campo obrigatório")
	private String profissao;
	
	@Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "O Campo dever ser neste padraõ (99) 99999-9999")
	private String telefone;
	
	@NotBlank(message = "Campo obrigatório")
	private String endereco;
	
	private Set<RoleDTO> roles = new HashSet<>();
	
	public UserDTO(User entity) {
        id = entity.getId();
        nome = entity.getNome();
        email = entity.getEmail();
        password = entity.getPassword();
        profissao = entity.getProfissao();
        telefone = entity.getTelefone();
        endereco = entity.getEndereco();
        entity.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
    }
}
