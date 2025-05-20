package com.softstream.vagas_online.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewPasswordDTO {
	
	@NotBlank(message = "Campo requerido")
	private String token;
	
	@NotBlank(message = "Campo requerido")
	@Size(min = 6, max = 16, message = "Deve ter no mínimo 6 caracteres")
	private String password;

}
