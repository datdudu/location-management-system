package br.com.pavscan.api.infra.persistence.Section;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SectionRepositoryInfra extends JpaRepository<SectionEntity, Long> {
    SectionEntity findByEmail(String email);
    @Query("SELECT AVG(s.rms) FROM SectionEntity s WHERE s.survey.id = :surveyId")
    Double averageOfRmsBySurveyId(@Param("surveyId") Long surveyId);
    @Query("SELECT AVG(s.rmsR1) FROM SectionEntity s WHERE s.survey.id = :surveyId")
    Double averageOfRmsR1BySurveyId(@Param("surveyId") Long surveyId);
    @Query("SELECT AVG(s.iri) FROM SectionEntity s WHERE s.survey.id = :surveyId")
    Double averageOfIriBySurveyId(@Param("surveyId") Long surveyId);
    @Query("SELECT AVG(s.iriR1) FROM SectionEntity s WHERE s.survey.id = :surveyId")
    Double averageOfIriR1BySurveyId(@Param("surveyId") Long surveyId);
    @Query("SELECT AVG(s.averageSpeed) FROM SectionEntity s WHERE s.survey.id = :surveyId")
    Double averageOfAverageSpeedBySurveyId(@Param("surveyId") Long surveyId);
    @Query("SELECT AVG(s.maxSpeed) FROM SectionEntity s WHERE s.survey.id = :surveyId")
    Double averageOfMaxSpeedBySurveyId(@Param("surveyId") Long surveyId);

    @Query("SELECT AVG(s.dist3) FROM SectionEntity s WHERE s.survey.id = :surveyId")
    Double averageOfDistanceBySurveyId(@Param("surveyId") Long surveyId);

    @Query("SELECT s FROM SectionEntity s WHERE s.survey.id = :surveyId ORDER BY s.dtStart ASC LIMIT 1")
    SectionEntity findFirstBySurveyId(@Param("surveyId") Long surveyId);

    @Query("SELECT s FROM SectionEntity s WHERE s.survey.id = :surveyId ORDER BY s.dtStart DESC LIMIT 1")
    SectionEntity findLastBySurveyId(@Param("surveyId") Long surveyId);
}
