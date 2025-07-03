package ch.wiss.m223.Football_Training.Check_In.App;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ch.wiss.m223.Football_Training.Check_In.App.model.ERole;
import ch.wiss.m223.Football_Training.Check_In.App.model.Role;
import ch.wiss.m223.Football_Training.Check_In.App.repository.RoleRepository;

@SpringBootApplication
public class FootballTrainingCheckInAppApplication implements CommandLineRunner{

	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(FootballTrainingCheckInAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (roleRepository.count() == 0) {
			roleRepository.save(new Role(ERole.ROLE_USER));
			roleRepository.save(new Role(ERole.ROLE_ADMIN));
		}
		
	}

}
