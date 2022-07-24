package SoulCode.Services.Services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import SoulCode.Services.Models.UsuarioJWT;
import SoulCode.Services.Repositories.UsuarioJWTRepository;
import SoulCode.Servicos.Data.DetalhesUsuarioData;

@Component
public class DetalhesUsuarioServiceImpl implements UserDetailsService{

	private final UsuarioJWTRepository usuarioJWTRepository;
	
	public DetalhesUsuarioServiceImpl(UsuarioJWTRepository usuarioJWTRepository) {
		this.usuarioJWTRepository = usuarioJWTRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UsuarioJWT> usuario = usuarioJWTRepository.findByLogin(username);
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuário não cadastro");
		}
		
		return new DetalhesUsuarioData(usuario);
	}

}
