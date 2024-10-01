package com.EducationSystem.Enrollement.DTO;

import lombok.Data;

import java.util.List;

@Data
public class CreateStudentRequest {
    private String studentName;
    private String studentPhone;
    private List<EnrollmentRequest> enrollments;

    @Data
    public static class EnrollmentRequest {
        private String courseCode;
    }
}
