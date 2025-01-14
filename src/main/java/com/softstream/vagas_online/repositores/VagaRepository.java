package com.softstream.vagas_online.repositores;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.softstream.vagas_online.entities.Vaga;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long> {

	@Query("select obj from Vaga obj where obj.status = 0 and upper(obj.titulo) like upper(concat('%', :titulo, '%')) and upper(obj.local) like upper(concat('%', :local, '%'))")
	Page<Vaga> searchOpenVacances(@Param(value = "titulo") String titulo, @Param(value = "local") String local, Pageable pageable);
	
	@Modifying
	@Query(nativeQuery = true, value = """
			update tb_vagas set status = 1 where id = :id """)
	void fecharVaga(@Param("id") Long id);
	
	@Query(nativeQuery = true, value = """
			select v.id, v.status, v.data_criacao_vaga, v.titulo, v.descricao, v.requisitos, v.data_realizacao_diaria,v.local, v.valor_diaria
			from tb_vagas v
			join tb_candidaturas c on v.id = c.vaga_id
			where c.user_id = :userId """)
	Page<Vaga> buscarCandidaturas(@Param("userId") Long userId, Pageable pageable);
}
