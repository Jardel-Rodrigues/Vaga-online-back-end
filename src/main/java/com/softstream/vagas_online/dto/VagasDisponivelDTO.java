package com.softstream.vagas_online.dto;

import java.time.LocalDate;

import com.softstream.vagas_online.entities.Vaga;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VagasDisponivelDTO {

	private Long id;
	private String titulo;
	private String descricao;
	private String requisitos;
	private LocalDate dataRealizacaoDiaria;
	private String local;
	private Double valorDiaria;

	public VagasDisponivelDTO(Vaga entity) {
		id = entity.getId();
		titulo = entity.getTitulo();
		descricao = entity.getDescricao();
		requisitos = entity.getRequisitos();
		dataRealizacaoDiaria = entity.getDataRealizacaoDiaria();
		local = entity.getLocal();
		valorDiaria = entity.getValorDiaria();
	}

}
