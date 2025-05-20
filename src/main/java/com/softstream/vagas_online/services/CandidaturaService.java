package com.softstream.vagas_online.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.softstream.vagas_online.dtos.CandidaturaDTO;
import com.softstream.vagas_online.dtos.user.UserDTO;
import com.softstream.vagas_online.dtos.vaga.VagaMinDTO;
import com.softstream.vagas_online.entities.Candidatura;
import com.softstream.vagas_online.entities.User;
import com.softstream.vagas_online.entities.Vaga;
import com.softstream.vagas_online.entities.pk.CandidaturaPK;
import com.softstream.vagas_online.repositores.CandidaturaRepository;
import com.softstream.vagas_online.repositores.VagaRepository;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Service
public class CandidaturaService {
	
	@Autowired
	private CandidaturaRepository candidaturaRepository;
	
	@Autowired 
	private VagaRepository vagaRepository;
	
	@Autowired
	private UserService userService;
	
	@Transactional(value = TxType.REQUIRED)
//	public CandidaturaDTO enviarCandidatura(CandidaturaDTO dto) {
//	    Candidatura entity = new Candidatura();
//	    copyDtoToEntity(dto, entity);
//	    entity = candidaturaRepository.save(entity);
//	    vagaRepository.fecharVaga(dto.getVagaId());
//
//	    // Utilize o método copyEntityToDto() para construir o DTO a partir da entidade
//	    CandidaturaDTO candidaturaDTO = CandidaturaDTO.builder()
//	            .userId(entity.getUserId())
//	            .vagaId(entity.getVagaId())
//	            .dataCandidatura(entity.getDataCandidatura())
//	            .status(entity.getStatus())
//	            .build();
//	    return candidaturaDTO;
//	}
	
	public CandidaturaDTO enviarCandidatura (CandidaturaDTO dto) {
		Candidatura entity = new Candidatura();
		copyDtoToEntity(dto, entity);
		entity = candidaturaRepository.save(entity);
		vagaRepository.fecharVaga(dto.getVagaId());
		return new CandidaturaDTO(entity);
	}
	
	@Transactional(value = TxType.SUPPORTS)
	public Page<VagaMinDTO> buscarCandidatura(Pageable pageable) {
		UserDTO userDTO = userService.getMe();
		Page<Vaga> page = vagaRepository.buscarCandidaturas(userDTO.getId(), pageable);
		return page.map(x -> new VagaMinDTO(x));
	}
	
	public void copyDtoToEntity(CandidaturaDTO dto, Candidatura entity) {
		CandidaturaPK candidaturaPK = new CandidaturaPK();

		UserDTO userDTO = userService.getMe();
		
		User user = new User();
	    user.setId(userDTO.getId());
	    candidaturaPK.setUser(user);
		
		Vaga vaga = new Vaga();
	    vaga.setId(dto.getVagaId()); 
	    candidaturaPK.setVaga(vaga);

	    entity.setId(candidaturaPK);
	}
}
