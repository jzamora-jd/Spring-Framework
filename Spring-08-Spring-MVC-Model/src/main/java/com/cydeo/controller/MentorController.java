package com.cydeo.controller;

import com.cydeo.enums.Gender;
import com.cydeo.model.Mentor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MentorController {

    @RequestMapping("/mentor")
    public String mentor(Model model){

        List<Mentor> mentors = new ArrayList<>();
        mentors.add(new Mentor("Daniel","Zamora",2, Gender.MALE));
        mentors.add(new Mentor("Tom","Hanks",65, Gender.MALE));
        mentors.add(new Mentor("Ammy","Bryan",25, Gender.FEMALE));
        model.addAttribute("mentors",mentors);

        return "/mentor/mentor-list";
    }
}
