package com.softstream.vagas_online.projections;

public interface UserDetailsProjection {
	
	String getUsername();
	String getPassword();
	Long getRoleId();
	String getAuthority();
}
