package SoulCode.Services.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import SoulCode.Services.Models.UsuarioJWT;

public interface UsuarioJWTRepository extends JpaRepository<UsuarioJWT, Integer>{
	
	public Optional<UsuarioJWT> findByLogin(String login);

}