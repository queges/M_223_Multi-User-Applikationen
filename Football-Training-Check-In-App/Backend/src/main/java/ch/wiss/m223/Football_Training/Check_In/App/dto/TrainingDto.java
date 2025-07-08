package ch.wiss.m223.Football_Training.Check_In.App.dto;

import ch.wiss.m223.Football_Training.Check_In.App.model.Training;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TrainingDto {
    private Long id;
    private LocalDateTime startTime;
    private List<AttendanceDto> attendances;

    public TrainingDto(Training training) {
        this.id = training.getId();
        this.startTime = training.getStartTime();
        this.attendances = training.getAttendances().stream()
            .map(AttendanceDto::new)
            .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public List<AttendanceDto> getAttendances() {
        return attendances;
    }
}
