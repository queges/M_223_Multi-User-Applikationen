package ch.wiss.m223.Football_Training.Check_In.App.repository;

import ch.wiss.m223.Football_Training.Check_In.App.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TrainingRepository extends JpaRepository<Training, Long> {
    List<Training> findByStartTimeAfterOrderByStartTimeAsc(LocalDateTime now);
    List<Training> findAll();
}