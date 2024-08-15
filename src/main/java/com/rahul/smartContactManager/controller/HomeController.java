package com.rahul.smartContactManager.controller;

import com.rahul.smartContactManager.dao.UserRepository;
import com.rahul.smartContactManager.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/test")
    @ResponseBody
    public String test(){
        return "working";
    }

    @RequestMapping("/")
    public String home(Model model){
        model.addAttribute("title","Home - Smart Contact Manager");
        return "home";
    }

    @RequestMapping("/about")
    public String about(Model model){
        model.addAttribute("title","About");
        return "about";
    }

    @RequestMapping("/signup")
    public String signUp(Model model){
        return "signup";
    }
}
