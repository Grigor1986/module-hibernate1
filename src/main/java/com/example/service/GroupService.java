package com.example.service;

import com.example.Group;
import com.example.Student;
import com.example.TransactionHelper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    private final SessionFactory sessionFactory;
    private final TransactionHelper helper;

    public GroupService(SessionFactory sessionFactory, TransactionHelper helper) {
        this.sessionFactory = sessionFactory;
        this.helper = helper;
    }

    public Group saveGroup(
            String number,
            Long gradYear
    ) {
        return helper.executeInTransaction(session -> {
            var group = new Group(number, gradYear);
            session.persist(group);
            return group;
        });
    }

    public List<Group> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("""
                    SELECT g FROM Group g
                    left join fetch g.studentList s
                    left join fetch s.profile
                    """, Group.class)
                    .list();
        }
    }
}
