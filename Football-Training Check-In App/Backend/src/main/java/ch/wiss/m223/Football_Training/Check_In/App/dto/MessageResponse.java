package ch.wiss.m223.Football_Training.Check_In.App.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class MessageResponse {
    private String message;

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}