package com.ds.blog.config;


import com.ds.blog.auth.PrincipalDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final PrincipalDetailService principalDetailService;

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
      throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public BCryptPasswordEncoder encode(){
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
    auth.userDetailsService(principalDetailService).passwordEncoder(encode());

     http
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests((authorizeRequests) ->
        authorizeRequests
            .requestMatchers("/","/auth/**", "/WEB-INF/**", "/js/**", "/css/**", "/image/**", "/favicon.ico").permitAll()
            .anyRequest().authenticated())
        .formLogin(login -> login.loginPage("/auth/loginForm")
            .loginProcessingUrl("/auth/loginProc")
            .defaultSuccessUrl("/")
        );
    return http.build();
  }

}
