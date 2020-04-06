package com.example.springsecuritydemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
   @Override
   protected void configure(HttpSecurity http) throws Exception {
      http
              .authorizeRequests()
              .antMatchers("/", "index", "/css/*", "/js/*")
              .permitAll()
              .anyRequest()
              .authenticated()
              .and()
              .httpBasic();   // refers to the type of authentication we want -> basicAuth
   }

   // override the default user: 'user' and password: 'Random_UUID'
   @Override
   @Bean       // Bean will instantiate this for us
   protected UserDetailsService userDetailsService() {
      UserDetails annaSmithUser = User.builder()
              .username("annasmith")
              .password("password")
              .roles("STUDENT")  // ROLE_STUDENT is how Spring Interpret this line.
              .build();

      return new InMemoryUserDetailsManager(annaSmithUser);
   }
}
