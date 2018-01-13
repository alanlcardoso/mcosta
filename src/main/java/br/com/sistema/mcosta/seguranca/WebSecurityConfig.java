package br.com.sistema.mcosta.seguranca;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
			.usersByUsernameQuery("select nome, senha, ativo from usuario where nome = ?")
			.authoritiesByUsernameQuery("select u.email, r.descricao from usuario u inner join usuario_permissao ur on (u.id  = ur.id_usuario) inner join permissao r on(r.id = ur.id_permissao) where u.email= ?")				
			.dataSource(dataSource)
			.passwordEncoder(bCryptPasswordEncoder);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/administracao/**").hasAuthority("ADMINISTRADOR")
				.anyRequest().permitAll()
			.and()
				.formLogin().loginPage("/login").permitAll()
				.failureUrl("/login?error=true").defaultSuccessUrl("/")
				.usernameParameter("nome").passwordParameter("senha")
			.and()
				.csrf();
	}
}