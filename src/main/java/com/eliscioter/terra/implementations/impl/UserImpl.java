package com.eliscioter.terra.implementations.impl;

import com.eliscioter.terra.implementations.services.UserService;
import com.eliscioter.terra.models.dto.UserDTO;
import com.eliscioter.terra.models.entity.UserEntity;
import com.eliscioter.terra.models.requests.CreateUserRequest;
import com.eliscioter.terra.models.wrapper.ResponseData;
import com.eliscioter.terra.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserImpl implements UserService {
    UserRepository userRepository;

    public UserImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseData fetchedUsers() {
        List<UserDTO> userDTOList = userRepository
                .findAll()
                .stream()
                .map(UserDTO::fromUser).collect(Collectors.toList());

        if (userDTOList.isEmpty()) {
            return new ResponseData().add("message", "No users found");
        }
        return new ResponseData()
                .add("users", userDTOList)
                .add("Total users", userDTOList.size());
    }

    @Override
    public ResponseData fetchedUser(UUID id) {
        Optional<UserDTO> user = getUser(id) != null
                ? Optional.of(UserDTO.fromUser(getUser(id)))
                : Optional.empty();

        return user
                .map(u -> new ResponseData().add("user", user))
                .orElseGet(() -> new ResponseData().add("message", "User does not exist"));
    }

    @Override
    public ResponseData createdUser(CreateUserRequest createUserRequest) {
        UserEntity user = new UserEntity();
        user.setEmail(createUserRequest.email());
        user.setUsername(createUserRequest.username());
        user.setPassword(createUserRequest.password());

        UserEntity createdUser = userRepository.save(user);

        return new ResponseData().add("user", UserDTO.fromUser(createdUser));
    }

    @Override
    public ResponseData updateUser(UUID id, CreateUserRequest updateUserRequest) {
        UserEntity user = getUser(id);

        if (user == null) {
            return new ResponseData().add("message", "User does not exist");
        }

        if (!updateUserRequest.email().equals(user.getEmail()) && !updateUserRequest.username().equals(user.getUsername())) {
            return new ResponseData().add("message", "Invalid credentials");
        }

        user.setPassword(updateUserRequest.password());
        user.setUpdatedAt(java.sql.Timestamp.valueOf(LocalDateTime.now()));

        UserEntity updatedUser = userRepository.save(user);

        return new ResponseData()
                .add("user", UserDTO.fromUser(updatedUser))
                .add("message", "User updated successfully");
    }

    @Override
    public ResponseData deleteUser(UUID id, CreateUserRequest deleteUserRequest) {
        if (deleteUserRequest.email() == null
                || deleteUserRequest.username() == null
                || deleteUserRequest.password() == null) {
            return new ResponseData().add("message", "Invalid credentials");
        }
        UserEntity user = getUser(id);

        if (user == null) {
            return new ResponseData().add("message", "User does not exist");
        }

        if (!deleteUserRequest.password().equals(user.getPassword())
                || !deleteUserRequest.username().equals(user.getUsername())
                || !deleteUserRequest.email().equals(user.getEmail())) {
            return new ResponseData().add("message", "Invalid credentials");
        }

        userRepository.delete(user);

        return new ResponseData().add("message", String.format("User %s deleted successfully", user.getUsername()));
    }

    private UserEntity getUser(UUID id) {
        return userRepository.findById(id).orElse(null);
    }
}
