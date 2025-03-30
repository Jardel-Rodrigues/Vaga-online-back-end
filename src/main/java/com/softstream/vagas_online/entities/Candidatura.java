package com.softstream.vagas_online.entities;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.softstream.vagas_online.entities.enums.StatusCandidatura;
import com.softstream.vagas_online.entities.pk.CandidaturaPK;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "tb_candidaturas")
public class Candidatura implements Serializable {
	
	@Serial
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CandidaturaPK id;
	
    @Builder.Default
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate dataCandidatura = LocalDate.now(ZoneId.of("America/Sao_Paulo"));
	
    @Builder.Default
    @Enumerated(EnumType.ORDINAL)
    private StatusCandidatura status = StatusCandidatura.ABERTA;

	@PrePersist
	public void prePersist() {
		if (dataCandidatura == null) {
			dataCandidatura = LocalDate.now(ZoneId.of("America/Sao_Paulo"));
		}
	}
}
