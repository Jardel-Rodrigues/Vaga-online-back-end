package com.softstream.vagas_online.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.softstream.vagas_online.dto.CandidaturaDTO;
import com.softstream.vagas_online.dto.VagaMinDTO;
import com.softstream.vagas_online.services.CandidaturaService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/candidaturas")
public class CandidaturaController {
	
	@Autowired
	private CandidaturaService candidaturaService;
	
	@SecurityRequirement(name = "bearerAuth")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(value = "/enviar-candidatura", produces = "application/json")
	public ResponseEntity<CandidaturaDTO> enviarCandidatura (@RequestBody CandidaturaDTO dto){
		CandidaturaDTO newDto = candidaturaService.enviarCandidatura(dto);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/user/{userId}/vaga/{vagaId}")
				.buildAndExpand(newDto.getUserId(), newDto.getVagaId())
				.toUri();
		return ResponseEntity.created(uri).body(newDto);
	}
	
	@SecurityRequirement(name = "bearerAuth")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "/minhas-candidaturas", produces = "application/json")
	public ResponseEntity<Page<VagaMinDTO>> buscarCandidaturas(Pageable pageable) {
		Page<VagaMinDTO> dto = candidaturaService.buscarCandidatura(pageable);
		return ResponseEntity.ok().body(dto);
	}
}
