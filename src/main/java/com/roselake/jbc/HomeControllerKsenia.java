package com.roselake.jbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class HomeControllerKsenia {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/addstudent")
    public String addStudent(Model model){
        model.addAttribute("student", new Student());
        model.addAttribute("allCourses", courseRepository.findAll());
        model.addAttribute("editing", "false");
        return "studentform";
    }

    @GetMapping("/editstudent/{id}")
    public String editStudent(@PathVariable("id") Long id, Model model){
        model.addAttribute("student", studentRepository.findById(id).get());
        model.addAttribute("allCourses", courseRepository.findAll());
        model.addAttribute("editing", "true");
        return "studentform";
    }

    @GetMapping("/deletestudent/{id}")
    public String deleteStudent(@PathVariable("id") Long id, Model model) {

        Student deleteStudent = studentRepository.findById(id).get();
        for (Course course : deleteStudent.getCourses() ){
            // loop through the courses and unlink the student from each course that it's connected to
            for (Student courseStudent : course.getStudents()){
                // check if the student id matches our student's id?
                // if it does, remove that "s" from this "c"
                if(courseStudent.getId()==deleteStudent.getId()){
                    course.deleteStudent(deleteStudent);
                }
            }
        }

        studentRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/viewstudent/{id}")
    public String viewStudent(@PathVariable("id") Long id, Model model){
        model.addAttribute("student", studentRepository.findById(id).get());
        return "studentdetail";
    }

    @GetMapping("/showstudents")
    public String showStudents(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        return "showstudents";
    }

    @PostMapping("/processstudent")
    public String processStudent(@Valid @ModelAttribute Student student, BindingResult result, Model model){
        return "redirect:/home";
    }

}

//// SAMPLE SOURCE CODE ::
//
//    /* PROCESS a Department, either new or edited
//     * redirect /home :: IF successful save of job, with or without an image
//     * departmentform :: IF binding result has ERRORS */
//    @PostMapping("/processdepartment")
//    public String processDepartment(@Valid Department department, BindingResult result, Model model) {
//
//        if (result.hasErrors()) {
//            // redisplay department form, this time with binding errors shown (via span th:if)
//            return "departmentform";
//        }
//
////        // taking this out!!! (will re-implement later. currently this causes problems on EDIT)
////        // if you re-implement, try to do it on the @Binding side of things, by putting an @Unique on the field in the class.
////        // check if the department exists ::
////        if (departmentRepository.countByName(department.getName()) > 0){
////            model.addAttribute("message", "Department '" + department.getName() + "' already exists!");
////            return "departmentform";
////        }
//
//        departmentRepository.save(department);
//
//        return "redirect:/home";           // redirect to home
//
//    }