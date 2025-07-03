package ch.wiss.m223.Football_Training.Check_In.App.repository;

import ch.wiss.m223.Football_Training.Check_In.App.model.ERole;
import ch.wiss.m223.Football_Training.Check_In.App.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}