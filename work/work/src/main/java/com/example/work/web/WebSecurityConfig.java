package com.example.work.web;


import com.example.work.Service.ServiceImpl.ContactServiceImpl;
import com.example.work.security.JwtAuthenticationEntryPoint;
import com.example.work.security.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@EnableWebSecurity

public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{

	@Autowired
	private ContactServiceImpl contactService;

	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;

	@Bean
	public JwtFilter jwtFilter()
	{
		return new JwtFilter();
	}

	@Bean
	public SessionRegistry sessionRegistry()
	{ return new SessionRegistryImpl();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.cors();
		http.csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler);
		http.authorizeRequests()
				.antMatchers("/savecontact","/getby/contact","savetype")
				.permitAll().anyRequest().authenticated().and().formLogin().permitAll();

		http.sessionManagement().maximumSessions(1).sessionRegistry(sessionRegistry());

		// Add our custom JWT security filter
		http.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
