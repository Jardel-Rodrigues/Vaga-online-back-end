package com.softstream.vagas_online.dto;

import java.time.LocalDate;

import com.softstream.vagas_online.entities.Vaga;
import com.softstream.vagas_online.entities.enums.StatusVaga;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VagaDTO {
	
	private Long id;
	
	@NotBlank(message = "Campo requerido")
	private String titulo;
	
	@Column(columnDefinition = "TEXT")
	private String descricao;
	
	@NotBlank(message = "Campo requerido")
	private String requisitos;
	
	private LocalDate dataCriacaoVaga;
	
	@NotNull(message = "Campo requerido")
	@Future(message = "A data deve ser uma data futura")
	private LocalDate dataRealizacaoDiaria;
		
	@NotBlank(message = "Campo requerido")
	private String local;
	
	@NotNull(message = "Campo requerido")
	@Positive(message = "O Valor deve ser maior que 0")
	private Double valorDiaria;
	
	private StatusVaga status;
	
	public VagaDTO(Vaga entity) {
		id = entity.getId();
		titulo = entity.getTitulo();
		descricao = entity.getDescricao();
		requisitos = entity.getRequisitos();
		dataCriacaoVaga = entity.getDataCriacaoVaga();
		dataRealizacaoDiaria = entity.getDataRealizacaoDiaria();
		local = entity.getLocal();
		valorDiaria = entity.getValorDiaria();
		status = entity.getStatus();
	}
	
}
