package com.rmj.CardPaymentMicroservice.security;

//import com.rmj.PayPalMicroservice.service.UserDetailsServiceImpl;
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

	/*@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public AuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
		AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
		authenticationTokenFilter.setAuthenticationManager(authenticationManagerBean());
		return authenticationTokenFilter;
	}*/

	// https://zoltanaltfatter.com/2018/05/15/spring-cloud-discovery-with-spring-boot-admin/
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				//.anonymous().disable()
				.cors()
				.and()
				.csrf().disable()
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
