package com.masai.school.cnfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.masai.school.Security.CustomUserDetailService;
import com.masai.school.Security.JwtAuthenticationEntryPoint;
import com.masai.school.Security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
	@Autowired
	private CustomUserDetailService customeUser;
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	 @Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http.
			csrf(csrf->csrf.disable())
				
				.cors(cors->cors.disable())
				.authorizeHttpRequests(auth->auth						
						.requestMatchers(HttpMethod.POST,"/api/auth/login").permitAll()
						.anyRequest().authenticated())
				
				
				.exceptionHandling(ex->ex.authenticationEntryPoint(jwtAuthenticationEntryPoint))
				.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
				
		
		
		http.addFilterBefore(jwtAuthenticationFilter,UsernamePasswordAuthenticationFilter.class);
		DefaultSecurityFilterChain build =http.build();
		return build;
			}
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(this.customeUser).passwordEncoder(passwordEncoder());
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }


}
