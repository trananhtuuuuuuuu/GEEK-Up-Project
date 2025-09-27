package com.example.technicalassessment.service;

import com.example.technicalassessment.domain.User;
import com.example.technicalassessment.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registrationUser(User user) {

        return this.userRepository.save(user);
    }
}
