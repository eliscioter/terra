package com.eliscioter.terra.implementations.impl;

import com.eliscioter.terra.implementations.services.UserService;
import com.eliscioter.terra.models.dto.UserDTO;
import com.eliscioter.terra.models.entity.UserEntity;
import com.eliscioter.terra.models.requests.CreateUserRequest;
import com.eliscioter.terra.models.requests.UpdateUserRequest;
import com.eliscioter.terra.models.wrapper.ResponseData;
import com.eliscioter.terra.repositories.UserRepository;
import com.eliscioter.terra.commons.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<ResponseData> fetchedUsers() {
        List<UserDTO> userDTOList = userRepository
                .findAll()
                .stream()
                .map(UserDTO::fromUser).collect(Collectors.toList());

        if (userDTOList.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body( new ResponseData().add("message", "No users found"));
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body( new ResponseData()
                .add("users", userDTOList)
                .add("Total users", userDTOList.size()));
    }

    @Override
    public ResponseEntity<ResponseData> fetchedUser(UUID id) {
        Optional<UserDTO> user = getUser(id) != null
                ? Optional.of(UserDTO.fromUser(getUser(id)))
                : Optional.empty();

        return user
                .map(u -> ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseData().add("user", user)))
                .orElseGet(() -> ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(new ResponseData().add("message", "User does not exist")));
    }

    @Override
    public ResponseEntity<ResponseData> createdUser(CreateUserRequest createUserRequest) {
        UserEntity user = new UserEntity();
        user.setEmail(createUserRequest.getEmail());
        user.setUsername(createUserRequest.getUsername());
        user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));

        try {
            UserEntity createdUser = userRepository.save(user);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ResponseData().add("user", UserDTO.fromUser(createdUser)));
        } catch (DataIntegrityViolationException e) {
            String errorMessage = e.getMessage().toLowerCase();
            if (errorMessage.contains("duplicate key") || errorMessage.contains("unique constraint")) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseData().add("message", "Username or Email address already exists."));
            } else {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseData().add("message", "Data integrity violation: " + e.getMessage()));
            }
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseData().add("message", "An error occurred: " + e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<ResponseData> updateUser(UUID id, UpdateUserRequest updateUserRequest) {
        UserEntity user = getUser(id);

        if (user == null) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new ResponseData().add("message", "User does not exist"));
        }

        if (!Util.verifyPassword(user.getPassword(), updateUserRequest.getOldPassword())
                || isUsernameEmailNotVerified(updateUserRequest, user)) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(new ResponseData().add("message", "Invalid credentials"));
        }

        user.setPassword(Util.hashPassword(updateUserRequest.getPassword()));
        user.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

        UserEntity updatedUser = userRepository.save(user);

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(new ResponseData()
                .add("user", UserDTO.fromUser(updatedUser))
                .add("message", "User updated successfully"));
    }

    @Override
    public ResponseEntity<ResponseData> deleteUser(UUID id) {
        UserEntity user = getUser(id);

        if (user == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseData().add("message", "User does not exist"));
        }

        userRepository.delete(user);

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(new ResponseData()
                        .add("message", String.format("User %s deleted successfully", user.getUsername())));
    }

    private UserEntity getUser(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    private boolean isUsernameEmailNotVerified(CreateUserRequest userRequest, UserEntity user) {
        return !userRequest.getEmail().equals(user.getEmail()) || !userRequest.getUsername().equals(user.getUsername());
    }
}
