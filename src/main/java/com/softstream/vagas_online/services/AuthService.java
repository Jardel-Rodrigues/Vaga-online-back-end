package com.softstream.vagas_online.services;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softstream.vagas_online.dtos.EmailDTO;
import com.softstream.vagas_online.dtos.NewPasswordDTO;
import com.softstream.vagas_online.entities.PasswordRecover;
import com.softstream.vagas_online.entities.User;
import com.softstream.vagas_online.repositores.PasswordRecoverRepository;
import com.softstream.vagas_online.repositores.UserRepository;
import com.softstream.vagas_online.services.exceptions.ResourceNotFoundException;

@Service
public class AuthService {
	
	@Value("${email.password-recover.uri}")
	private String recoverUri;

	@Value("${email.password-recover.token.minutes}")
	private Long tokenMinutes;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private PasswordRecoverRepository passwordRecoverRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmailService emailService;
	
	@Transactional
	public void createRecoverToken(EmailDTO dto) {
		
		User user = userRepository.findByEmail(dto.getEmail());
		
		if (user == null) {
			throw new ResourceNotFoundException("Email not found");
		}

		String token = UUID.randomUUID().toString();

		PasswordRecover entity = new PasswordRecover();
		entity.setToken(token);
		entity.setExpiration(Instant.now().plusSeconds(tokenMinutes * 60L));
		entity.setEmail(dto.getEmail());
		passwordRecoverRepository.save(entity);

		String body = "Acesse o link para definir uma nova senha (válido por " + tokenMinutes + " minutos):\n\n" + recoverUri + token;

		emailService.sendEmail(dto.getEmail(), "Recuperação de senha", body);
	}

	@Transactional
	public void saveNewPassword(NewPasswordDTO dto) {
		List<PasswordRecover> list = passwordRecoverRepository.searchValidTokens(dto.getToken(), Instant.now());

		if (list.isEmpty()) {
			throw new ResourceNotFoundException("Invalid token");
		}

		User user = userRepository.findByEmail(list.get(0).getEmail());
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		userRepository.save(user);
	}
	
	protected User authenticated() {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
			String username = jwtPrincipal.getClaim("username");
			return userRepository.findByEmail(username);
		}
		catch (Exception e) {
			throw new UsernameNotFoundException("Email not found");
		}
	}
	
}
