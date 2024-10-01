package com.EducationSystem.Enrollement.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enrollmentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    @JsonIgnore
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "course_code")
    @JsonBackReference

    private Course course;
    @Column(name = "enrollmentDate")
    private LocalDate enrollmentDate;



    @PrePersist
    public void prePersist() {
        this.enrollmentDate = LocalDate.now();
    }
}
