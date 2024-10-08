package com.cydeo.controller;

import com.cydeo.dto.CourseDTO;
import com.cydeo.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses/api/v2")//api version 2
public class CourseController_ResponseEntity {

    private final CourseService courseService;

    public CourseController_ResponseEntity(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses(){//allows user to send headers
        return ResponseEntity
                .status(HttpStatus.ACCEPTED) //has the code that will be returned
                .header("Version","Cydeo.V2")//headers needed
                .header("Operation","Get List")
                .body(courseService.getCourses());
    }


    @GetMapping("{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable("id") Long courseId){
        return ResponseEntity.ok(courseService.getCourseById(courseId));//return 200 Ok status
    }

    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO course){
        return ResponseEntity
                .status(HttpStatus.CREATED)//201 created response
                .header("Operation","Create")//headers passed during call
                .body(courseService.createCourse(course));
    }

}
