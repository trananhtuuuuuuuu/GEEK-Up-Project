package com.example.technicalassessment.service.user;

import com.example.technicalassessment.Exception.UserNotFoundException;
import com.example.technicalassessment.domain.User;
import com.example.technicalassessment.dto.Meta;
import com.example.technicalassessment.dto.ResultPaginationDTO;
import com.example.technicalassessment.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registrationUser(User user) {
        return this.userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow(
                () -> new UserNotFoundException("User with email " + email + " not found")
        );
    }

    public ResultPaginationDTO getAllUsers(Pageable pageable) {
        Page<User> users = this.userRepository.findAll(pageable);

        ResultPaginationDTO resultPaginationDTO = new ResultPaginationDTO();
        Meta mt = new Meta();

        mt.setPage(users.getNumber() + 1);
        mt.setPageSize(users.getSize());
        mt.setPages(users.getTotalPages());
        mt.setTotal((int) users.getTotalElements());

        resultPaginationDTO.setMeta(mt);
        resultPaginationDTO.setResult(users.getContent());

        return resultPaginationDTO;
    }


}
