package com.example;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "student_group")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number")
    private String number;

    @Column(name = "grad_year")
    private Long graduationDate;

    @OneToMany(mappedBy = "group", fetch = FetchType.EAGER)
    private List<Student> studentList;

    public Group() {
    }

    public Group(String number, Long graduationDate) {
        this.number = number;
        this.graduationDate = graduationDate;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(Long graduationDate) {
        this.graduationDate = graduationDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
