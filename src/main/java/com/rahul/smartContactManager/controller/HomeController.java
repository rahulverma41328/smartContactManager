package com.rahul.smartContactManager.controller;

import com.rahul.smartContactManager.dao.UserRepository;
import com.rahul.smartContactManager.entities.User;
import com.rahul.smartContactManager.helper.Message;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
        model.addAttribute("user",new User());
        return "signup";
    }

    // handler for registering user
    @RequestMapping(value = "/do_register",method = RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user, @RequestParam(value = "agreement",
            defaultValue = "false") boolean agreement, Model model, HttpSession session){

        try{
            if (!agreement){
                System.out.println("You have not agreed T&C");
                throw new Exception("you have not agreed the terms and condition");
            }
            user.setRole("ROLE_USER");
            user.setEnabled(true);

            User result = this.userRepository.save(user);

            System.out.println("Agreement "+ agreement);
            System.out.println("USER " + result );
            model.addAttribute("user",new User());
            session.setAttribute("message",new Message("Succesfully Registered","alert-success"));
            return "signup";

        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("user",user);
            session.setAttribute("message",new Message("Something went wrong"+ e.getMessage(),"alert-danger"));
            return "signup";
        }

    }
}
