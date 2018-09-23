package com.example.DBService.security;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
private Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
@Autowired
DataSource datasource;

@Override
protected void configure(HttpSecurity http) throws Exception {
http.authorizeRequests()
//.anyRequest().authenticated()

             .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
            // .antMatchers("/**").access("hasRole('ROLE_USER')")
             .and().formLogin().loginPage("/login").permitAll().failureUrl("/login?error")
             .usernameParameter("username").passwordParameter("password")
             .and()
             .logout().logoutSuccessUrl("/login?logout")
             .and()
             .exceptionHandling().accessDeniedPage("/403")
             .and()
             .csrf();
             }

@Override
public void configure(AuthenticationManagerBuilder auth) throws Exception {
	
  auth.jdbcAuthentication().dataSource(datasource)
	.usersByUsernameQuery(
		"select username,password, enabled from users where username=?")
	.authoritiesByUsernameQuery(
		"select username, role from user_roles where username=?");
}	

/*
@Autowired
public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
{
	
	
	
logger.info("BCrypt Encoded Password Encoder :-"+retBCryptPassEncoder().encode("deep@1177"));
    
	/*   PasswordEncoder encoder1 = new BCryptPasswordEncoder(22);
logger.info(encoder1.encode("deepanshu"));
logger.info("BCryptPasswordEncoder :- "+encoder1.encode("deep@1177"));
logger.info("Pbkdf2PasswordEncoder :- "+encoder2.encode("deep@1177"));
logger.info("SCryptPasswordEncoder :- "+encoder3.encode("deep@1177"));

    auth
	     .inMemoryAuthentication()
	         .withUser("deepanshu").password(retBCryptPassEncoder().encode("deep@1177")).roles("USER");
}

@Bean
public PasswordEncoder retBCryptPassEncoder()
{
	return new BCryptPasswordEncoder();
}




*/


@Bean
@SuppressWarnings("deprecation")
public static NoOpPasswordEncoder noPasswordEncoder()
{
	return (NoOpPasswordEncoder)NoOpPasswordEncoder.getInstance(); 
}


}



