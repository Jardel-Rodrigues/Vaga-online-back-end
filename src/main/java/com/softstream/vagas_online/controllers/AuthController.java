package com.softstream.vagas_online.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softstream.vagas_online.dtos.EmailDTO;
import com.softstream.vagas_online.dtos.NewPasswordDTO;
import com.softstream.vagas_online.services.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;

	@PostMapping(value = "/recover-token")
	public ResponseEntity<Void> createRecoverToken(@Valid @RequestBody EmailDTO dto) {
		authService.createRecoverToken(dto);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/new-password")
	public ResponseEntity<Void> saveNewPassword(@Valid @RequestBody NewPasswordDTO dto) {
		authService.saveNewPassword(dto);
		return ResponseEntity.noContent().build();
	}

}
