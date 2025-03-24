package com.softstream.vagas_online.repositores;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.softstream.vagas_online.entities.User;
import com.softstream.vagas_online.projections.UserDetailsProjection;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
	
	@Query(nativeQuery = true, value = """
			select u.email as username, u.password, r.id as roleId, r.authority
			from vagas_online.tb_user u
			join vagas_online.tb_user_role ur on u.id = ur.user_id
			join vagas_online.tb_role r on r.id = ur.role_id
			where u.email = :email """)
	List<UserDetailsProjection> searchUserAndRolesByEmail(String email);

}
