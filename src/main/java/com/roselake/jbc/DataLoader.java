package com.roselake.jbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

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
            User faculty = new User("faculty", "faculty", "Victor", "P.", "victor@mc.edu", true);
            User user = new User("user", "user", "User", "Last", "user@user.com", true);


            //************************************************
            // Linking Users and Roles :: for Security Layer
            //************************************************
            faculty.setRoles(Arrays.asList(userRole));
            userRepository.save(faculty);
            user.setRoles(Arrays.asList(userRole));
            userRepository.save(user);


            //************************************************
            // ADMIN :: for Monday...
            //************************************************
            //User admin = new User("admin", "admin", "Admin", "Last", "admin@admin.com",true);
            //admin.setRoles(Arrays.asList(adminRole));
            //userRepository.save(admin);


            //*******************************************
            // Making Courses ::
            //      courses first, then students
            //*******************************************
            Course jbc = new Course("Java Boot Camp", "Eight-Week java web development boot camp", "Victor");
            Course dataanalysis = new Course ("Data Analysis", "Five-Week data wrangling boot camp", "Kenisha");
            Course security = new Course ("Security Plus", "Two-week intensive security class", "Josh");
            courseRepository.save(jbc);
            courseRepository.save(dataanalysis);
            courseRepository.save(security);

            //*******************************************
            // Making Students ::
            //*******************************************
            Student soheila = new Student("Soheila", "Escobar", "1991-11-29");
            Student roselake = new Student ("Rose", "Lake", "1978-07-04");
            Student kevin = new Student ("Kevin", "Cheung", "1995-05-15");
            Student shayla = new Student ("Shayla", "Brock", "1996-12-05");
            studentRepository.save(soheila);
            studentRepository.save(roselake);
            studentRepository.save(kevin);
            studentRepository.save(shayla);

            //*******************************************
            // Linking Students to their Courses ::
            //*******************************************
            soheila.setCourses(Arrays.asList(jbc, dataanalysis));
            roselake.setCourses(Arrays.asList(jbc, security));
            kevin.setCourses(Arrays.asList(jbc));
            shayla.setCourses(Arrays.asList(jbc,dataanalysis,security));
            studentRepository.save(soheila);
            studentRepository.save(roselake);
            studentRepository.save(kevin);
            studentRepository.save(shayla);

            //*******************************************
            // Linking Courses to their Students ::
            //*******************************************
            jbc.setStudents(Arrays.asList(soheila, roselake, kevin, shayla));
            courseRepository.save(jbc);
            dataanalysis.setStudents(Arrays.asList(soheila, kevin));
            courseRepository.save(dataanalysis);
            security.setStudents(Arrays.asList(roselake, shayla));
            courseRepository.save(security);

        }

    }

}
