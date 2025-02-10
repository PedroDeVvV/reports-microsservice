package com.tracevia.app.core.service;

import com.tracevia.app.core.entities.User;
import com.tracevia.app.core.repository.UserRepository;
import com.tracevia.app.infra.exception.RegistrationException;
import com.tracevia.app.infra.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class RegistrationService {

    @Autowired
    private UserRepository repository;

    public User registerUser(User data) {

        var user = repository.findByLogin(data.getLogin());
        if (user != null) {
            throw new RegistrationException("Este login já existe");
        }

        repository.save(data);

        return data;
    }
}
