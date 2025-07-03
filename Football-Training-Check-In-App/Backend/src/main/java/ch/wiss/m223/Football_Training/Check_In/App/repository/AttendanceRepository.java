package ch.wiss.m223.Football_Training.Check_In.App.repository;

import ch.wiss.m223.Football_Training.Check_In.App.model.Attendance;
import ch.wiss.m223.Football_Training.Check_In.App.model.Training;
import ch.wiss.m223.Football_Training.Check_In.App.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    Optional<Attendance> findByUserAndTraining(User user, Training training);
    List<Attendance> findByTraining(Training training);
}