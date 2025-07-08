package ch.wiss.m223.Football_Training.Check_In.App.dto;

import ch.wiss.m223.Football_Training.Check_In.App.model.Attendance;
import ch.wiss.m223.Football_Training.Check_In.App.model.User;
import ch.wiss.m223.Football_Training.Check_In.App.model.AttendanceStatus;

public class AttendanceDto {
    private String username;
    private AttendanceStatus status;

    public AttendanceDto(Attendance attendance) {
        User user = attendance.getUser();
        this.username = (user != null) ? user.getUsername() : "Unbekannt";
        this.status = attendance.getStatus();
    }

    public String getUsername() {
        return username;
    }

    public AttendanceStatus getStatus() {
        return status;
    }
}
