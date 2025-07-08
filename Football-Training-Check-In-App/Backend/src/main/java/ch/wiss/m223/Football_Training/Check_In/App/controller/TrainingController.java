package ch.wiss.m223.Football_Training.Check_In.App.controller;

import ch.wiss.m223.Football_Training.Check_In.App.dto.AttendanceDto;
import ch.wiss.m223.Football_Training.Check_In.App.dto.TrainingDto;
import ch.wiss.m223.Football_Training.Check_In.App.model.*;
import ch.wiss.m223.Football_Training.Check_In.App.repository.*;
import ch.wiss.m223.Football_Training.Check_In.App.security.services.UserDetailsImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/trainings")
public class TrainingController {

    @Autowired
    private TrainingRepository trainingRepo;

    @Autowired
    private AttendanceRepository attendanceRepo;

    @Autowired
    private UserRepository userRepo;

    // GET all Trainings (für CoachDashboard + PlayerDashboard)
    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingRepo.findAll().stream()
            .map(TrainingDto::new)
            .collect(Collectors.toList());
    }

    // POST neues Training
    @PostMapping
    public ResponseEntity<?> createTraining(@RequestBody Map<String, String> body) {
        LocalDateTime startTime = LocalDateTime.parse(body.get("startTime"));
        Training training = new Training(startTime);
        trainingRepo.save(training);
        return ResponseEntity.ok("Training erstellt");
    }

    // POST Teilnahme setzen
    @PostMapping("/{id}/attendance")
    public ResponseEntity<?> setAttendance(
        @PathVariable Long id,
        @RequestBody Map<String, String> body,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Optional<Training> trainingOpt = trainingRepo.findById(id);
        if (trainingOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Training nicht gefunden");
        }

        Training training = trainingOpt.get();
        User user = userRepo.findById(userDetails.getId()).orElseThrow();

        AttendanceStatus status = AttendanceStatus.valueOf(body.get("status"));
        Attendance attendance = attendanceRepo.findByUserAndTraining(user, training)
            .orElse(new Attendance(user, training, status));
        attendance.setStatus(status);
        attendanceRepo.save(attendance);

        return ResponseEntity.ok("Teilnahme gespeichert");
    }

    // GET Teilnehmerübersicht für ein Training (optional)
    @GetMapping("/{id}/attendance")
    public ResponseEntity<?> getAttendanceList(@PathVariable Long id) {
        Optional<Training> trainingOpt = trainingRepo.findById(id);
        if (trainingOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Training nicht gefunden");
        }

        Training training = trainingOpt.get();
        List<Attendance> attendances = attendanceRepo.findByTraining(training);

        List<AttendanceDto> response = attendances.stream()
            .map(AttendanceDto::new)
            .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
}
