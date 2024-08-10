package br.com.pavscan.api.infra.persistence.Highway;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HighwayRepositoryInfra extends JpaRepository<HighwayEntity, Long> {
    HighwayEntity findByName(String name);
}
