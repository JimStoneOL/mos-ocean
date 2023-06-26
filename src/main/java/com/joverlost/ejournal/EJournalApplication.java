package com.joverlost.ejournal;

import com.joverlost.ejournal.entity.Role;
import com.joverlost.ejournal.entity.enums.ERole;
import com.joverlost.ejournal.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EJournalApplication {

    @Autowired
    private RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(EJournalApplication.class, args);
    }

    @Bean
    public CommandLineRunner CommandLineRunnerBean() {
        return (args) -> {
            if (
                    roleRepository.findByName(ERole.ROLE_ADMIN).isPresent()
            ) {

            } else {
                roleRepository.deleteAll();
                Role student = new Role();
                student.setName(ERole.ROLE_ADMIN);
                roleRepository.save(student);
            }
        };
    }
}
