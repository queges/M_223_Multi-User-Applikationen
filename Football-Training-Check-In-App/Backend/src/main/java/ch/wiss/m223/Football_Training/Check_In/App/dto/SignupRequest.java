package ch.wiss.m223.Football_Training.Check_In.App.dto;

import java.util.Set;
 
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
 
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
 
@Setter
@Getter
public class SignupRequest {
 
    private String username;
    private String email;
    private String password;
    private Set<String> roles;
}