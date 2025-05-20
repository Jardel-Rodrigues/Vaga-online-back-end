package com.softstream.vagas_online.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.softstream.vagas_online.dtos.RoleDTO;
import com.softstream.vagas_online.dtos.user.UserDTO;
import com.softstream.vagas_online.dtos.user.UserInsertDTO;
import com.softstream.vagas_online.dtos.user.UserUpdateDTO;
import com.softstream.vagas_online.entities.Role;
import com.softstream.vagas_online.entities.User;
import com.softstream.vagas_online.projections.UserDetailsProjection;
import com.softstream.vagas_online.repositores.RoleRepository;
import com.softstream.vagas_online.repositores.UserRepository;
import com.softstream.vagas_online.services.exceptions.ResourceNotFoundException;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private AuthService authService;
	
	@Transactional(value = TxType.REQUIRED)
	public UserDTO createAcount(UserInsertDTO dto) {
		User entity = new User();
		copyDtoToEntity(dto, entity);
		entity.getRoles().clear();
		Role role = roleRepository.findByAuthority("ROLE_CANDIDATO");
		entity.getRoles().add(role);
		entity = userRepository.save(entity);
		return new UserDTO(entity);
	}
	
	@Transactional(value = TxType.REQUIRED)
	public UserDTO updateAcount(Long id, UserUpdateDTO dto) {
		try {
			User entity = userRepository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = userRepository.save(entity);
			return new UserDTO(entity);
		} catch (Exception e) {
			throw new ResourceNotFoundException("%s not fount ".formatted(id));
		}
	}
	
	@Transactional(value = TxType.REQUIRED)
	public Page<UserDTO> findAllPaged(Pageable pageable) {
		Page<User> page = userRepository.findAll(pageable);
		return page.map(x -> new UserDTO(x));
	}
		
	@Transactional(value = TxType.SUPPORTS)
	public UserDTO getMe() {
		User user = authService.authenticated();
		return new UserDTO(user);
	}
	
	private void copyDtoToEntity(UserDTO dto, User entity) {
		entity.setEmail(dto.getEmail());
		entity.setPassword(passwordEncoder.encode(dto.getPassword()));
		entity.setNome(dto.getNome());
		entity.setProfissao(dto.getProfissao());
		entity.setTelefone(dto.getTelefone());
		entity.setEndereco(dto.getEndereco());
		entity.getRoles().clear();
		
		for(RoleDTO roleDto : dto.getRoles()) {
			Role role = roleRepository.getReferenceById(roleDto.getId());
			entity.getRoles().add(role);
		}
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {		
		
		List<UserDetailsProjection> result = userRepository.searchUserAndRolesByEmail(username);			
		
		if(result.isEmpty()) {
			throw new UsernameNotFoundException("User not found");
		}		
		User user = new User();
		user.setEmail(username);
		user.setPassword(result.get(0).getPassword());
		for(UserDetailsProjection projection : result) {
			user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
		}		
		return user;
	}
}
