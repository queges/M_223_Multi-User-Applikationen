package ch.wiss.m223.Football_Training.Check_In.App.dto;

import ch.wiss.m223.Football_Training.Check_In.App.model.AttendanceStatus;

public class AttendanceDTO {
    private String username;
    private AttendanceStatus status;

    public AttendanceDTO(String username, AttendanceStatus status) {
        this.username = username;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public AttendanceStatus getStatus() {
        return status;
    }
}