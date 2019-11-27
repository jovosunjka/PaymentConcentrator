package com.rmj.BitcoinMicroservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) // ovo smo ubacili da bismo u controllerima mogli
													//koristiti anotaciju @PreAuthorize
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				//.anonymous().disable()
				.cors()
				.and()
				.csrf()
				.disable()
				.exceptionHandling()
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				//.sessionAuthenticationStrategy(sessionAuthenticationStrategy())
				.and()
				.authorizeRequests()
				//.antMatchers("/pki/**").hasAuthority("SECURE_ADMINISTRATOR")
				.anyRequest().permitAll();
		
		// Custom JWT based authentication
		//httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
	}
}
