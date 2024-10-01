package com.EducationSystem.Enrollement.Controllers;

import com.EducationSystem.Enrollement.DTO.CreateStudentRequest;
import com.EducationSystem.Enrollement.Model.Course;
import com.EducationSystem.Enrollement.Model.Enrollment;
import com.EducationSystem.Enrollement.Model.Student;
import com.EducationSystem.Enrollement.Services.CourseService;
import com.EducationSystem.Enrollement.Services.EnrollmentService;
import com.EducationSystem.Enrollement.Services.StudentService;
import com.EducationSystem.Enrollement.Repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private EnrollmentService enrollmentService;
    @Autowired
    private CourseRepository CourseRepository;

    @PostMapping("/savestudent")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        // Check if enrollments are present and handle them
        if (student.getEnrollments() != null) {
            for (Enrollment enrollment : student.getEnrollments()) {
                // Extract courseCode from the JSON
                String courseCode = enrollment.getCourse().getCourseCode();

                // Fetch the course by courseCode from the repository
                Course course = CourseRepository.findById(courseCode)
                        .orElseThrow(() -> new RuntimeException("Course not found: " + courseCode));

                // Set the course in the enrollment
                enrollment.setCourse(course);
                enrollment.setStudent(student); // Also set the student in the enrollment
            }
        }

        // Save the student and the enrollments
        Student savedStudent = studentService.saveStudent(student);
        return ResponseEntity.ok(savedStudent);
    }

    @GetMapping("/liststudents")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/getstudent/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long studentId) {
        return studentService.getStudentById(studentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
