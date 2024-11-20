package com.softstream.vagas_online.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmailDTO {
	
	@NotBlank(message = "Campo requerido")
	@Email(message = "Email inválido")
	private String email;

}
