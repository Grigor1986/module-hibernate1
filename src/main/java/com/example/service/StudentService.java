package com.example.service;

import com.example.Student;
import com.example.TransactionHelper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {

    private final SessionFactory sessionFactory;
    private final TransactionHelper helper;

    public StudentService(SessionFactory sessionFactory, TransactionHelper helper) {
        this.sessionFactory = sessionFactory;
        this.helper = helper;
    }

    public Student saveStudent(Student student) {
        return helper.executeInTransaction(session -> {
            session.persist(student);
            return student;
        });
    }

    public Student updateStudent(Student student) {
        return helper.executeInTransaction(session -> {
            return session.merge(student);
        });
    }


    public Student getById(Long id) {
        try(Session session = sessionFactory.openSession()) {
            return session.get(Student.class, id);
        }
    }

    public void deleteStudent(Long id) {
        helper.executeInTransaction(session -> {
            Student studentForDelete = session.get(Student.class, id);
            session.remove(studentForDelete);
        } );
   }

      public List<Student> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT s FROM Student s", Student.class).list();
        }
      }

}


