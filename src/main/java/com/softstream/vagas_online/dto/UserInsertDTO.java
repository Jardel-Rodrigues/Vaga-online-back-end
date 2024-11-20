package com.softstream.vagas_online.dto;

import com.softstream.vagas_online.services.validation.UserInsertValid;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@UserInsertValid
public class UserInsertDTO extends UserDTO {

	private String passaword;
	
}
