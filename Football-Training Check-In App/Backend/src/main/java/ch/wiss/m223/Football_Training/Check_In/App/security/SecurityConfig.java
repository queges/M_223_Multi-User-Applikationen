package ch.wiss.m223.Football_Training.Check_In.App.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.http.HttpMethod;

import ch.wiss.m223.Football_Training.Check_In.App.security.jwt.AuthTokenFilter;
import ch.wiss.m223.Football_Training.Check_In.App.security.services.UserDetailsServiceImpl;
 
@Configuration
@EnableMethodSecurity
public class SecurityConfig {
  
  
 
  @Autowired
  private UserDetailsServiceImpl UserDetailsServiceImpl;
 
  @Autowired
  private AuthenticationEntryPoint unauthorizedHandler;
 
  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(UserDetailsServiceImpl);
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }
 
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
    return authConfig.getAuthenticationManager();
  }
 
  @Bean
  public AuthTokenFilter authenticationJwtTokenFilter() {
    return new AuthTokenFilter();
  }
 
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
 
private static final String[] EVERYONE = {"/public", "/api/auth/**"};
 @Bean
 public SecurityFilterChain filterChain(HttpSecurity http)
throws Exception {
 http.csrf(csrf -> csrf.disable()) //disable Cross-Site Request Forgery (CSRF) prevention
.cors(Customizer.withDefaults()) //configure CORS: Cross Origin Request Sharing
.authorizeHttpRequests(auth -> {
auth.requestMatchers(HttpMethod.POST, "/items").hasRole("ADMIN");
auth.requestMatchers(EVERYONE).permitAll()
.anyRequest().authenticated(); } )
 .formLogin(Customizer.withDefaults()) //für Login-Form im Browser
 .httpBasic(Customizer.withDefaults()); // für CURL, Postman, Insomnia
 return http.build();
 }
}