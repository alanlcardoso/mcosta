package br.com.sistema.mcosta.seguranca;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(new Md5PasswordEncoder())
				.usersByUsernameQuery("select nome, senha from usuario where nome = ?")
		.authoritiesByUsernameQuery(
				"select nome, 'ROLE_ADMINISTRADOR'  from usuario where nome = ?");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/administrador/**").access("hasRole('ROLE_ADMINISTRADOR')")
			.and()
				.formLogin().loginPage("/login").failureUrl("/login?error")
			.and()
				.logout().logoutUrl("/logout").logoutSuccessUrl("/")
			.and()
				.csrf();
			
		// http.authorizeRequests()
		// .antMatchers("/admin/**").access("hasAnyRole('ROLE_SUP','ROLE_ADMIN')")
		// .and()
		// .formLogin().loginPage("/login").failureUrl("/login?error")
		// .usernameParameter("usuario").passwordParameter("senha")
		// .and()
		// .logout().logoutUrl("/logout").logoutSuccessUrl("/")
		// .and()
		// .exceptionHandling().accessDeniedPage("/erro/403")
		//
		// .and()
		// .csrf();
	}
}
