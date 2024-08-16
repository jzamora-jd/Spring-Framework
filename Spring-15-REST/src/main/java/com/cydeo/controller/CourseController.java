package com.cydeo.controller;

import com.cydeo.dto.CourseDTO;
import com.cydeo.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // @Controller + @ResponseBody - replaces view UI
@RequestMapping("/courses/api/v1")//parent endpoint for all api calls
public class CourseController {

    private final CourseService courseService; //inject course service

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping//GET for endpoint /courses/api/v1
    public List<CourseDTO> getAllCourses(){
        List<CourseDTO> list = courseService.getCourses();
        return list;

    }

    @GetMapping("{id}")//this will only append to parent endpoint
    public CourseDTO getCourseById(@PathVariable("id") Long courseId){
        return courseService.getCourseById(courseId);
    }

    @GetMapping("category/{name}") // getting category name using pathVariable
    public List<CourseDTO> getCourseByCategory(@PathVariable("name") String category){
        return courseService.getCoursesByCategory(category);

    }

    @PostMapping
    public CourseDTO createCourse(@RequestBody CourseDTO course){//course is coming from post body

        return courseService.createCourse(course);
    }

    @PutMapping("{id}")
    public void updateCourse(@PathVariable("id") Long courseId,@RequestBody CourseDTO course){
        courseService.updateCourse(courseId,course);
    }

    @DeleteMapping("{id}")
    public void deleteCourseById(@PathVariable("id") Long courseId){
        courseService.deleteCourseById(courseId);
    }

    @DeleteMapping
    public void deleteCourses(){
        courseService.deleteCourses();
    }






































}
