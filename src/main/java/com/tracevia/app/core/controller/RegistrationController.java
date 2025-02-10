package com.tracevia.app.core.controller;

import com.tracevia.app.core.dto.DetailsDataUser;
import com.tracevia.app.core.dto.RegistrationData;
import com.tracevia.app.core.entities.User;
import com.tracevia.app.core.repository.UserRepository;
import com.tracevia.app.core.service.RegistrationService;
import com.tracevia.app.core.service.UserService;
import com.tracevia.app.infra.configuration.SecurityConfigurations;
import com.tracevia.app.infra.exception.RegistrationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private SecurityConfigurations config;

    @Autowired
    private UserRepository repository;

    @Autowired
    private RegistrationService service;

    @PostMapping
    public ResponseEntity register(@RequestBody @Valid RegistrationData data, UriComponentsBuilder uriBuilder) throws RegistrationException {

        var pass = config.passwordEncoder().encode(data.password());
        LocalDateTime now = LocalDateTime.now();

        var user = new User(null, data.login(), pass, now);

        var userService = service.registerUser(user);

        var uri = uriBuilder.path("/registration/{login}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetailsDataUser(userService.getUsername()));
    }
}
