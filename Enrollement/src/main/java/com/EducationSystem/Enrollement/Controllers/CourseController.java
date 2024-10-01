package com.EducationSystem.Enrollement.Controllers;

import com.EducationSystem.Enrollement.DTO.CourseResponse;
import com.EducationSystem.Enrollement.Model.Course;
import com.EducationSystem.Enrollement.Services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/savecourse")
    public ResponseEntity<?> createCourse(@RequestBody Course course) {
        Course savedCourse = courseService.saveCourse(course);
        return ResponseEntity.ok(new CourseResponse(savedCourse.getCourseCode(), savedCourse.getCourseTitle()));
    }

    @PostMapping("/savecoursesbatch")
    public ResponseEntity<?> saveCoursesBatch(@RequestBody List<Course> courses) {
        List<CourseResponse> savedCourses = courseService.saveCoursesBatch(courses).stream()
                .map(course -> new CourseResponse(course.getCourseCode(), course.getCourseTitle()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(savedCourses);
    }

    @GetMapping("/listcourses")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/getcourse/{courseCode}")
    public ResponseEntity<Course> getCourseByCode(@PathVariable String courseCode) {
        return courseService.getCourseByCode(courseCode)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
