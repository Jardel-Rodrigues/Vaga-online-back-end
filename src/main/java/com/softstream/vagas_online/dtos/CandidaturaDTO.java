package com.softstream.vagas_online.dtos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.softstream.vagas_online.entities.Candidatura;
import com.softstream.vagas_online.entities.enums.StatusCandidatura;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CandidaturaDTO {
	
	private Long userId;
    private Long vagaId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataCandidatura;

    private StatusCandidatura status;
    
    public CandidaturaDTO(Candidatura entity) {
        userId = entity.getId().getUser().getId();
        vagaId = entity.getId().getVaga().getId();
        dataCandidatura = entity.getDataCandidatura();
        status = entity.getStatus();
    }
    
//	public CandidaturaDTO copyEntityToDto() {
//		return builder()
//				.userId(userId)
//				.vagaId(vagaId)
//				.dataCandidatura(dataCandidatura)
//				.status(status)
//				.build();
//	}
    
}
