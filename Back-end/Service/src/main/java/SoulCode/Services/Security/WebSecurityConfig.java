package SoulCode.Services.Security;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Desabilitando a autoriação das requisições (Ninguém tem autorização)
 * O usuário tem que estar autenticado na plataforma
 * e independente se tem autorização ou não
 * e qualquer pessoa e usuário vai ter acesso a tela de login
 * o usuário quiser fazer logout
 * fazer requisição de logout
 */
//@Configuration
//@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

private static final String[] PUBLIC_ENDPOINTS_ADMIN = {
			"servicos**/**",
			"/servicos/orcamento**/**",
      
};

private static final String[] PUBLIC_ENDPOINTS_USER = {
            "/servicos/funcionario**/**",
            "/servicos/servico**/**"
};
	
	@Autowired
	private ImplementsUserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable().authorizeRequests()
		//.antMatchers(HttpMethod.GET, "/").permitAll()
		//.antMatchers(HttpMethod.GET, PUBLIC_ENDPOINTS_USER).hasAnyRole("USER", "ADMIN")
		//.antMatchers(HttpMethod.GET, PUBLIC_ENDPOINTS_ADMIN).hasRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/servicos**/**").permitAll()
		.antMatchers(HttpMethod.POST, "/servicos**/**").permitAll()
		//.antMatchers(HttpMethod.PUT, "/servicos**/**").permitAll()
		//.antMatchers(HttpMethod.DELETE, "/servicos**/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin().permitAll()
		.and()
		.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/materialize/**", "/style/**");
	}
	
}
