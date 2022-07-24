package SoulCode.Services.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import SoulCode.Services.Models.UsuarioJWT;
import SoulCode.Services.Repositories.UsuarioJWTRepository;

@Service
public class UsuarioJWTService {
	
	@Autowired
	UsuarioJWTRepository usuarioJWTRepository;
	
	public List<UsuarioJWT> listarUsuarioJWT(){
		return usuarioJWTRepository.findAll();
	}
	
	public UsuarioJWT inserirUsuario(UsuarioJWT usuarioJWT) {
		return usuarioJWTRepository.save(usuarioJWT);
	}

}
