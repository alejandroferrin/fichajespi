package com.fichajespi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fichajespi.security.jwt.JwtEntryPoint;
import com.fichajespi.security.jwt.JwtTokenFilter;
import com.fichajespi.security.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MainSecurity extends WebSecurityConfigurerAdapter {

	private final String RRHH = "RRHH";
	private final String USER = "USER";

	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	JwtEntryPoint jwtEntryPoint;

	@Bean
	public JwtTokenFilter jwtTokenFilter() {
		return new JwtTokenFilter();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/v2/api-docs",
				"/configuration/ui",
				"/swagger-resources/**",
				"/configuration/security",
				"/swagger-ui.html",
				"/webjars/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
				.cors().and()
				.csrf().disable()
				.authorizeRequests()
				.antMatchers("/calendario/**").hasRole(RRHH)
				.antMatchers("/dia/**").hasRole(RRHH)
				.antMatchers("/fichaje/now").permitAll()
//				.antMatchers("/fichaje/now").hasRole(USER)
				.antMatchers("/fichaje/pagesFiltered").hasRole(USER)
				.antMatchers("/fichaje/listFiltered").hasRole(USER)
				.antMatchers("/fichaje/**").hasRole(RRHH)
				.antMatchers("/permiso/create").hasRole(USER)
				.antMatchers("/permiso/pagesFiltered").hasRole(USER)
				.antMatchers("/permiso/listFiltered").hasRole(USER)
				.antMatchers("/permiso/**").hasRole(RRHH)
				.antMatchers("/usuario/password/**").hasRole(USER)
				.antMatchers("/usuario/miusuario").hasRole(USER)
				.antMatchers("/usuario/**").hasRole(RRHH)
				.antMatchers("/vacaciones/create").hasRole(USER)
				.antMatchers("/vacaciones/pagesFiltered").hasRole(USER)
				.antMatchers("/vacaciones/listFiltered").hasRole(USER)
				.antMatchers("/vacaciones/**").hasRole(RRHH)
				.antMatchers("/auth/nuevo").hasRole(RRHH)
				.antMatchers("/auth/login").permitAll()
				.antMatchers("/test/**").permitAll()
				//.antMatchers("/**").permitAll() // Usar para testear la api sin login
				.anyRequest().authenticated()
				.and()
				.exceptionHandling().authenticationEntryPoint(jwtEntryPoint)
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(jwtTokenFilter(),
				UsernamePasswordAuthenticationFilter.class);
	}

}
