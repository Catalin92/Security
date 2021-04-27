package com.example.demo.configuration;

import com.example.demo.repositories.UserRepository;
import com.example.demo.security.filters.CsrfTokenLoggerFilter;
import com.example.demo.security.filters.CustomCsrfTokenRepository;
import com.example.demo.security.filters.TokenAuthenticationFilter;
import com.example.demo.security.filters.UsernameAndPasswordFilter;
import com.example.demo.security.providers.OtpAuthenticationProvider;
import com.example.demo.security.providers.TokenAuthProvider;
import com.example.demo.security.providers.UsernamePasswordAuthProvider;
import com.example.demo.services.JPAUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;

@Configuration
@EnableWebSecurity(debug = true)
public class ProjectConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsernamePasswordAuthProvider authProvider;

	@Autowired
	private OtpAuthenticationProvider otpAuthenticationProvider;

	@Autowired
	private TokenAuthProvider tokenAuthProvider;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	public UserDetailsService userDetailsService(){
		return new JPAUserDetailsService();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(authProvider)
				.authenticationProvider(otpAuthenticationProvider)
				.authenticationProvider(tokenAuthProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterAt(usernamePasswordAuthFilter(),
				BasicAuthenticationFilter.class)
				.addFilterAfter(tokenAuthFilter(),
						BasicAuthenticationFilter.class )
//				.csrf().disable()
		;

		http.csrf(c -> {
//			c.ignoringAntMatchers("/csrfdiabled/**");
            c.csrfTokenRepository(new CustomCsrfTokenRepository());
		});

		http.addFilterAfter(new CsrfTokenLoggerFilter(),
				CsrfFilter.class
		);
	}

	@Bean
	public TokenAuthenticationFilter tokenAuthFilter() {
		return new TokenAuthenticationFilter();
	}

	@Bean
	public UsernameAndPasswordFilter usernamePasswordAuthFilter() {
		return new UsernameAndPasswordFilter();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}


//	@Bean
//	public JPAUserDetailsService jpaUserDetailsService(){
//		return new JPAUserDetailsService();
//	}
//
//	@Override
//	@Bean
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		return super.authenticationManagerBean();
//	}
//	@Bean
//	public void creation(){
//		User user = new User();
//		user.setUsername("admin");
//		user.setPassword("admin");
//
//		userRepository.save(user);
//
//	}

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) {
//		auth.authenticationProvider(customAuthenticationProvider);
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.addFilterAt(filter, BasicAuthenticationFilter.class);
//
//		http.authorizeRequests().anyRequest().permitAll();
////		http.httpBasic();
//
////		http.csrf().disable(); // CSRF tokens ...
//
//	}
}

