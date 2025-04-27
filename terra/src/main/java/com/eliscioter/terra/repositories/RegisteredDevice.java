package com.eliscioter.terra.repositories;

import com.eliscioter.terra.models.entity.RegisteredDeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface RegisteredDevice extends JpaRepository<RegisteredDeviceEntity, Long> {

    @Query("SELECT r.deviceToken FROM RegisteredDeviceEntity r")
    Collection<String> findAllDeviceToken();
    RegisteredDeviceEntity findByCorrelationToken(String id);

}
