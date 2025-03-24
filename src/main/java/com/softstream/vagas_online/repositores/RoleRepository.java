package com.softstream.vagas_online.repositores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softstream.vagas_online.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
	public Role findByAuthority(String authority);

}
