package br.com.locationManagementSystem.api.infra.persistence.Location;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepositoryInfra extends JpaRepository<LocationEntity, Long> {
    Optional<LocationEntity> findByName(String name);
}
