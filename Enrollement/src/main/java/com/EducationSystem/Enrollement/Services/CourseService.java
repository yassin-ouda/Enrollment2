package com.EducationSystem.Enrollement.Services;

import com.EducationSystem.Enrollement.Model.Course;
import com.EducationSystem.Enrollement.Repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> saveCoursesBatch(List<Course> courses) {
        return courseRepository.saveAll(courses);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseByCode(String courseCode) {
        return courseRepository.findById(courseCode);
    }
}
