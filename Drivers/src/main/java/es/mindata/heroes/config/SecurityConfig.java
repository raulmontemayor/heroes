package es.mindata.heroes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.httpBasic().and().formLogin().and().authorizeRequests().antMatchers(HttpMethod.GET, "/error/**")
				.anonymous().antMatchers(HttpMethod.GET, "/**").hasRole("USER").antMatchers(HttpMethod.GET, "/hero")
				.hasRole("USER").antMatchers(HttpMethod.GET, "/hero/**").hasRole("USER")
				.antMatchers(HttpMethod.POST, "/hero").hasRole("USER").antMatchers(HttpMethod.PATCH, "/hero/**")
				.hasRole("USER").antMatchers(HttpMethod.DELETE, "/hero/**").hasRole("USER").and().csrf().disable();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService());

	}

	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		@SuppressWarnings("deprecation")
		UserDetails user = User.withDefaultPasswordEncoder().username("admin").password("adminadmin").roles("USER")
				.build();

		return new InMemoryUserDetailsManager(user);
	}
}
