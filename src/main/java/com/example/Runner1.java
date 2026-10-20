package com.example;

import com.example.service.CourseService;
import com.example.service.GroupService;
import com.example.service.ProfileService;
import com.example.service.StudentService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDateTime;
import java.util.List;

public class Runner1 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.example");
        SessionFactory sessionFactory = context.getBean(SessionFactory.class);
//        Session session = sessionFactory.openSession();
        StudentService studentService = context.getBean(StudentService.class);
        ProfileService profileService = context.getBean(ProfileService.class);
        GroupService groupService = context.getBean(GroupService.class);
        CourseService courseService = context.getBean(CourseService.class);
        Group group1 = groupService.saveGroup("123", 2020L);
        Group group2 = groupService.saveGroup("124", 2022L);
        Course course1 = new Course("math-1", "math");
        Course course2 = new Course("math-2", "math");
        Course course3 = new Course("math-3", "math");

        Student student1 = new Student("Ivan", 22, group1 );
        Student student2 = new Student("Victor", 23, group1);
        Student student3 = new Student("Fox", 21, group1);
        studentService.saveStudent(student1);
        studentService.saveStudent(student2);
        studentService.saveStudent(student3);
        courseService.saveCourse(course1);
        courseService.saveCourse(course2);
        courseService.saveCourse(course3);

        courseService.enrollStudentToCourse(2L, 2L);
        courseService.enrollStudentToCourse(3L, 2L);

        Student student = studentService.getById(2L);
        System.out.println(student);

//        Profile profile1 = new Profile("My bio", LocalDateTime.now(), student1);
//        profileService.saveProfile(profile1);
     groupService.findAll();

    }
}
