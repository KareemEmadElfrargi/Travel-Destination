package com.fawry.task.backend.traveldestination;

import com.fawry.task.backend.traveldestination.model.Role;
import com.fawry.task.backend.traveldestination.model.User;
import com.fawry.task.backend.traveldestination.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
public class TravelDestinationApplication implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(TravelDestinationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = User.builder()
                    .username("kareem")
                    .password(passwordEncoder.encode("8561"))
                    .role(Role.ADMIN)
                    .build();
            userRepository.save(admin);
            System.out.println("Default Admin User Created");
        }
    }
}
