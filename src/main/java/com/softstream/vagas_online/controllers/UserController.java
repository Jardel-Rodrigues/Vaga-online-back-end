package com.softstream.vagas_online.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.softstream.vagas_online.dto.UserDTO;
import com.softstream.vagas_online.dto.UserInsertDTO;
import com.softstream.vagas_online.dto.UserUpdateDTO;
import com.softstream.vagas_online.services.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
 	
	@PostMapping(value = "/create-acount", produces = "application/json")
	public ResponseEntity<UserDTO> createAcount(@Valid @RequestBody UserInsertDTO dto) {
		UserDTO newDto = userService.createAcount(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newDto.getId()).toUri();
		return ResponseEntity.created(uri).body(newDto);
	}
	
	@SecurityRequirement(name = "bearerAuth")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping(value = "/update-acount/{id}", produces = "application/json")
	public ResponseEntity<UserDTO> updateAcount(@PathVariable Long id, @Valid @RequestBody UserUpdateDTO dto) {
		UserDTO newDto = userService.updateAcount(id, dto);
		return ResponseEntity.ok().body(newDto);
	}
	
	@SecurityRequirement(name = "bearerAuth")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "/find-all-paged", produces = "application/json")
	public ResponseEntity<Page<UserDTO>> findAllPaged(Pageable pegeable){
		Page<UserDTO> newDto = userService.findAllPaged(pegeable);
		return ResponseEntity.ok().body(newDto);
	}
	
	@SecurityRequirement(name = "bearerAuth")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CANDIDATO', 'ROLE_RH', 'ROLE_GERENTE')")
	@GetMapping(value = "/get-me",  produces = "application/json")
	public ResponseEntity<UserDTO> getMe() {
		UserDTO dto = userService.getMe();
		return ResponseEntity.ok(dto);
	}
}
