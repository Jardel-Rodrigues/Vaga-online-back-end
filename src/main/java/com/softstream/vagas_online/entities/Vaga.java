package com.softstream.vagas_online.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.softstream.vagas_online.entities.enums.StatusVaga;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "tb_vagas")
public class Vaga implements Serializable {
	private static final long serialVersionUID = 1L;
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	
	@Column(columnDefinition = "TEXT")
	private String descricao;
	private String requisitos;
	
	@Column(columnDefinition = "DATE")
    @Builder.Default
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate dataCriacaoVaga = LocalDate.now(ZoneId.of("America/Sao_Paulo"));
	
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataRealizacaoDiaria;
	
	private String local;
	private Double valorDiaria;
	
    @Enumerated(EnumType.ORDINAL)
    @Builder.Default
    private StatusVaga status = StatusVaga.ABERTA;
	
    @PrePersist
    public void prePersist() {
        if (dataCriacaoVaga == null) {
            dataCriacaoVaga = LocalDate.now(ZoneId.of("America/Sao_Paulo"));
        }
    }
}
