package com.example.technicalassessment.service.user;

import com.example.technicalassessment.Exception.UserNotFoundException;
import com.example.technicalassessment.domain.User;
import com.example.technicalassessment.dto.Meta;
import com.example.technicalassessment.dto.ResultPaginationDTO;
import com.example.technicalassessment.repository.UserRepository;
import com.example.technicalassessment.response.role.RoleResponse;
import com.example.technicalassessment.response.user.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public ResultPaginationDTO getAllUsers(Specification<User> specification, Pageable pageable) {

        Page<User> users = this.userRepository.findAll(specification, pageable);

        ResultPaginationDTO resultPaginationDTO = new ResultPaginationDTO();
        Meta mt = new Meta();

        mt.setPage(users.getNumber() + 1);
        mt.setPageSize(users.getSize());
        mt.setPages(users.getTotalPages());
        mt.setTotal((int) users.getTotalElements());

        resultPaginationDTO.setMeta(mt);
        resultPaginationDTO.setResult(users.getContent());


        List<UserResponse> userResponseList = new ArrayList<>();
        for (User user : users.getContent()) {
            UserResponse userResponse = new UserResponse(
                    user.getName(),
                    user.getEmail(),
                    user.getGender(),
                    user.getPhone(),
                    user.getProvince(),
                    user.getDistrict(),
                    user.getCommune(),
                    user.getAddress(),
                    user.getHousingType(),
                    new RoleResponse(user.getRole().getId(),  user.getRole().getName())
            );

            userResponseList.add(userResponse);
        }

        resultPaginationDTO.setResult(userResponseList);

        return resultPaginationDTO;
    }

    public User getUserById(Long id) {
        return this.userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User with id " + id + " not found")
        );
    }

    public User updateUserById(Long id, User user) {
        return null;
    }


    public void deleteUserById(Long id) {
        User user = this.userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User with id " + id + " not found")
        );
        this.userRepository.deleteById(id);
    }

    public void updateUserToken(String token, String email) {
        User user = this.getUserByEmail(email);
        user.setRefreshToken(token);
        this.userRepository.save(user);
    }

    public User getUserByRefreshTokenAndEmail(String refreshToken, String email) {
        return this.userRepository.findByRefreshTokenAndEmail(refreshToken, email);
    }


}
