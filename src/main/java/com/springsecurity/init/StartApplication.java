package com.springsecurity.init;

import com.springsecurity.model.domain.User;
import com.springsecurity.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class StartApplication implements CommandLineRunner {
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        User user = repository.findByUsername("admin");
        if (user == null) {
            user = new User();
            user.setName("ADMIN");
            user.setUsername("admin");
            user.setPassword(encoder.encode("admin123"));
            user.getRoles().add("ADMIN");
            repository.save(user);
        }

        user = repository.findByUsername("Daniel");
        if (user == null) {
            user = new User();
            user.setName("Daniel");
            user.setUsername("Daniel");
            user.setPassword(encoder.encode("daniel123"));
            user.getRoles().add("ADMIN");
            user.getRoles().add("USER");
            repository.save(user);
        }

        user = repository.findByUsername("user");
        if (user == null) {
            user = new User();
            user.setName("USER");
            user.setUsername("user");
            user.setPassword(encoder.encode("user123"));
            user.getRoles().add("USER");
            repository.save(user);
        }
    }
}