package com.komal.springboot.myfirstwebapp.Security;

import java.util.function.Function;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
	
	
	private HttpSecurity disable;

	@Bean
	@SuppressWarnings("deprecation")
	public InMemoryUserDetailsManager createUserDetailsManager()
	{
		
		UserDetails u1 = createUserDetails("komal", "komal");
		UserDetails u2 = createUserDetails("mohit", "komal");
		return new InMemoryUserDetailsManager(u1,u2);
	}

	private UserDetails createUserDetails(String username, String password) 
	{
		Function<String, String> passwordEncoder = input->passwordEncoder().encode(input);
		UserDetails userDetails = User.builder().
									passwordEncoder(passwordEncoder)
									.username(username)
									.password(password)
									.roles("ADMIN","USER")
									.build();
		return userDetails;
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@SuppressWarnings("removal")
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity https) throws Exception
	{
		https.authorizeHttpRequests(auth->auth.anyRequest().authenticated());
		https.formLogin(withDefaults());
		disable = https.csrf().disable();
		https.headers().frameOptions().disable();
		return https.build();
	}
}
