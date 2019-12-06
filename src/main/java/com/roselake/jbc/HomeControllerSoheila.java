package com.roselake.jbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class HomeControllerSoheila {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    CourseRepository courseRepository;

    @GetMapping("/addcourse")
    public String courseForm(Model model){
        model.addAttribute("course", new Course());
        return "courseform";
    }

    @GetMapping("/editcourse/{id}")
    public String editCourse(@PathVariable("id") Long id, Model model){
        model.addAttribute("course", courseRepository.findById(id).get());
        return "courseform";
    }

    @GetMapping("/deletecourse/{id}")
    public String deleteCourse(@PathVariable("id") Long id, Model model){
        courseRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/viewcourse/{id}")
    public String viewCourse(@PathVariable("id") Long id, Model model){
        model.addAttribute("course", courseRepository.findById(id).get());
        return "coursedetail";
    }

    @PostMapping("/processcourse")
    public String processCourse(@Valid @ModelAttribute Course course, BindingResult result){
        if(result.hasErrors()){
            return "courseform";
        }
        courseRepository.save(course);
        return "redirect:/";
    }

}
