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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.softstream.vagas_online.dto.VagaDTO;
import com.softstream.vagas_online.dto.VagasDisponivelDTO;
import com.softstream.vagas_online.services.VagaService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/vagas")
public class VagaController {
	
	@Autowired
	private VagaService service;
	
	@SecurityRequirement(name = "bearerAuth")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RH')")
	@PostMapping(value = "/criar-vaga", produces = "application/json")
	public ResponseEntity<VagaDTO> createVacancy(@Valid @RequestBody VagaDTO dto) {
		VagaDTO newDto = service.createVacancy(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newDto.getId()).toUri();
		return ResponseEntity.created(uri).body(newDto);
	}
	
	@SecurityRequirement(name = "bearerAuth")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RH')")
	@PutMapping(value = "/atualizar-vaga/{id}", produces = "application/json")
	public ResponseEntity<VagasDisponivelDTO> updateVacance(
			@PathVariable Long id, 
			@Valid @RequestBody VagaDTO dto) {
		VagasDisponivelDTO newDto = service.updateVacance(id, dto);
		return ResponseEntity.ok().body(newDto);
	}
	
	@SecurityRequirement(name = "bearerAuth")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "/buscar-todas-as-vagas", produces = "application/json")
	public ResponseEntity<Page<VagaDTO>> searchAllVacances(Pageable pageable){
		Page<VagaDTO> page = service.searchAllVacances(pageable);
		return ResponseEntity.ok().body(page);
	}
	
	@SecurityRequirement(name = "bearerAuth")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CANDIDATO', 'ROLE_RH', 'ROLE_GERENTE')")
	@GetMapping(value = "/buscar-vagas-aberta", produces = "application/json")
	public ResponseEntity<Page<VagasDisponivelDTO>> searchOpenVacances (
			@RequestParam(defaultValue = "") String titulo, 
			@RequestParam(defaultValue = "") String local, Pageable pageable) {
		Page<VagasDisponivelDTO> page = service.searchOpenVacances(titulo, local, pageable);
		return ResponseEntity.ok().body(page);
	}
	
}
