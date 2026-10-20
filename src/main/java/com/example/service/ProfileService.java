package com.example.service;

import com.example.Profile;
import com.example.TransactionHelper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    private final SessionFactory sessionFactory;
    private final TransactionHelper helper;

    public ProfileService(SessionFactory sessionFactory, TransactionHelper helper) {
        this.sessionFactory = sessionFactory;
        this.helper = helper;
    }

    public Profile saveProfile(Profile profile) {
        return helper.executeInTransaction(session -> {
            session.persist(profile);
            return profile;
        });
    }
}
