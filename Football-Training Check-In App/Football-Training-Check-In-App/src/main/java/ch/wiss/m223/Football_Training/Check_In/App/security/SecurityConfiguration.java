package ch.wiss.m223.Football_Training.Check_In.App.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
 
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
  @Bean
  public UserDetailsService users(@Autowired PasswordEncoder pwEnc){
    UserDetails user = User.builder()
    .username("user")
    .password(pwEnc.encode("top"))
    .roles("USER")
    .build();
    UserDetails admin = User.builder()
    .username("admin")
    .password(pwEnc.encode("secret"))
    .roles("USER", "ADMIN")
    .build();
    return new InMemoryUserDetailsManager(user, admin);
  }
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
 
 
  private static final String[] EVERYONE = {"/public"};
 
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
 