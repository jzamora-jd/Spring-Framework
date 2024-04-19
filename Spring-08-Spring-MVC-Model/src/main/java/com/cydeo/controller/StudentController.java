package com.cydeo.controller;

import com.cydeo.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class StudentController {

    @RequestMapping("/welcome")
    public String homePage(Model model){

        model.addAttribute("name","Daniel");
        model.addAttribute("course","AI Robotics");

        String subject = "Zamora";
        model.addAttribute("subject",subject);

        int student_id = new Random().nextInt();
        model.addAttribute("student_id",student_id);

        List<Integer> numbers = new ArrayList<>();
        numbers.add(34);
        numbers.add(340);
        numbers.add(40);
        numbers.add(89);
        model.addAttribute("numbers", numbers);

        Student student = new Student(120721,"Daniel","Zamora");
        model.addAttribute("student",student);

        return "/student/welcome";
    }
}
