package br.com.pavscan.api.infra.persistence.Survey;

import br.com.pavscan.api.infra.persistence.Highway.HighwayEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepositoryInfra extends JpaRepository<SurveyEntity, Long> {
    SurveyEntity findByName(String name);
}
