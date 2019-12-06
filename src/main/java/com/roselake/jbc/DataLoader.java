package com.roselake.jbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... strings) throws Exception {

        if (roleRepository.count() == 0) {


            //*******************************************
            // Making Roles :: for Security Layer
            //*******************************************
            roleRepository.save(new Role("USER"));
            roleRepository.save(new Role("ADMIN"));
            Role adminRole = roleRepository.findByRole("ADMIN");
            Role userRole = roleRepository.findByRole("USER");


            //*******************************************
            // Making Users :: for Security Layer
            //*******************************************
            User faculty = new User("faculty", "faculty", "Kevin", "Cheung", "kevin@cheung.com", true);
            faculty.setRoles(Arrays.asList(userRole));
            userRepository.save(faculty);

            User user = new User("user", "user", "User", "Last", "user@user.com", true);
            user.setRoles(Arrays.asList(userRole));
            userRepository.save(user);

            // save for later :: project week and/or Monday's project ::
            // this Data Loads a default admin user
            //User admin = new User("admin", "admin", "Admin", "Last", "admin@admin.com",true);
            //admin.setRoles(Arrays.asList(adminRole));
            //userRepository.save(admin);


            //*******************************************
            // Making Courses ::
            //      courses first, then students
            //*******************************************

            //*******************************************
            // Making Students ::
            //*******************************************


        }

    }

}
