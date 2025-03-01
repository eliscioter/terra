package com.eliscioter.terra.repositories;

import com.eliscioter.terra.model.dto.UserDTO;
import com.eliscioter.terra.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
}
