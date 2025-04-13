package com.softstream.vagas_online.repositores;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softstream.vagas_online.entities.Candidatura;
import com.softstream.vagas_online.entities.pk.CandidaturaPK;

public interface CandidaturaRepository extends JpaRepository<Candidatura, CandidaturaPK> {


}
