package com.softstream.vagas_online.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.softstream.vagas_online.dto.VagaDTO;
import com.softstream.vagas_online.dto.VagasDisponivelDTO;
import com.softstream.vagas_online.entities.Vaga;
import com.softstream.vagas_online.repositores.VagaRepository;
import com.softstream.vagas_online.services.exceptions.ResourceNotFoundException;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Service
public class VagaService {
	
	@Autowired
	private VagaRepository vagaRepository;
	
	@Transactional(value = TxType.REQUIRED)
	public VagaDTO createVacancy(VagaDTO dto) {
		Vaga entity = new Vaga();
		copyDtoToEntity(entity, dto);
		entity = vagaRepository.save(entity);
		return new VagaDTO(entity);
	}
	
	@Transactional(value = TxType.REQUIRED)
	public VagasDisponivelDTO updateVacance(Long id, VagaDTO dto) {
		try {
			Vaga entity = vagaRepository.getReferenceById(id);
			copyDtoToEntity(entity, dto);
			return new VagasDisponivelDTO(entity);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Id not found" + id);
		}
	}
	
	@Transactional(value = TxType.SUPPORTS)
	public Page<VagaDTO> searchAllVacances(Pageable pageable) {
		Page<Vaga> page = vagaRepository.findAll(pageable);
		return page.map(x -> new VagaDTO(x));
	}
	
	public Page<VagasDisponivelDTO> searchOpenVacances(String titulo, String local, Pageable pageable) {
		Page<Vaga> page = vagaRepository.searchOpenVacances(titulo, local, pageable);
		return page.map(x -> new VagasDisponivelDTO(x));
	}
	
	public void copyDtoToEntity(Vaga entity, VagaDTO dto) {
		entity.setTitulo(dto.getTitulo());
		entity.setDescricao(dto.getDescricao());
		entity.setRequisitos(dto.getRequisitos());
		entity.setLocal(dto.getLocal());
		entity.setValorDiaria(dto.getValorDiaria());
		entity.setDataRealizacaoDiaria(dto.getDataRealizacaoDiaria());
	}
}
