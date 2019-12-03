package com.roselake.jbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(Model model){
        if (userService.getUser() != null) {
            model.addAttribute("user_id", userService.getUser().getId());
        }
        return "index";
    }

    @RequestMapping("/login")
    public String login(Model model){

        // this bit of code is especially good here, as logout redirects to "/login?logout"
        if (userService.getUser() != null) {
            model.addAttribute("user_id", userService.getUser().getId());
        }

        return "login";

    }

    @RequestMapping("/admin")
    public String admin(Principal principal, Model model){
        String username = principal.getName();
        model.addAttribute("user", userRepository.findByUsername(username));
        if (userService.getUser() != null) {
            model.addAttribute("user_id", userService.getUser().getId());
        }
        return "admin";
    }

    @RequestMapping("/secure")
    public String secure(Principal principal, Model model){
        String username = principal.getName();
        model.addAttribute("user", userRepository.findByUsername(username));
        if (userService.getUser() != null) {
            model.addAttribute("user_id", userService.getUser().getId());
        }
        return "secure";
    }

    @GetMapping("/register")
    public String showRegistrationPage(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/register")
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user,
                                          BindingResult result,
                                          Model model) {

        // if there are validation errors ::
        if (result.hasErrors()) { return "registration"; }

        // if the username already exists ::
        if (userService.countByUsername(user.getUsername()) > 0) {
            model.addAttribute("message", "username already exists");
            return "registration";
        }

        // if the email already exists ::
        if (userService.countByEmail(user.getEmail()) > 0) {
            model.addAttribute("message", "this email has already been used to register");
            return "registration";
        }

        // if we get this far, then the username and email are valid, and we can move on...
        userService.saveUser(user);
        model.addAttribute("message", "User Account Created");
        model.addAttribute("user", user);
        model.addAttribute("user_id", userService.getUser().getId());
        return "index";

    }

}
