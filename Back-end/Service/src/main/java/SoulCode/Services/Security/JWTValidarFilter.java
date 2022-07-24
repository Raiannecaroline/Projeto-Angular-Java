package SoulCode.Services.Security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class JWTValidarFilter extends BasicAuthenticationFilter{

	public JWTValidarFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	public static final String HEADER_ATRIBUTO = "Authorization";
	
	public static final String ATRIBUTO_PREFIXO = "Bearer ";
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, 
									HttpServletResponse response, 
									FilterChain chain) throws IOException,
									ServletException{
		
		String atributo = request.getHeader(HEADER_ATRIBUTO);
	
		/**
		 * Que tem um atributo que tem um token e retorna um bearer
		 */
		
		if(atributo == null) {
			chain.doFilter(request, response);
			return;
		}
		
		if(!atributo.startsWith(ATRIBUTO_PREFIXO)) {
			chain.doFilter(request, response);
			return;
		}
		
		/**
		 * Estamos substituindo o prefixo Bearer
		 */
		String token = atributo.replace(ATRIBUTO_PREFIXO, "");
		
		/**
		 * Autenticar o Token
		 */
		UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(token);
		
		/**
		 * Usado para armazenar os dados do usuário autenticado
		 */
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		chain.doFilter(request, response);
		
	}
	
	private UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {
		
		String usuario = JWT.require(Algorithm.HMAC512(JWTAutenticarFilter.TOKEN_SENHA))
				.build() //Construir
				.verify(token) //Verificar o Token
				.getSubject();
		
		/**
		 * Se não houver ele vai retornar nulo
		 */
		if(usuario == null) {
			return null;
		}
		
		/**
		 * Retornar um username com a autenticação do seu Token.
		 */
		return new UsernamePasswordAuthenticationToken(usuario, null,
				new ArrayList<>());
		
	}

}
