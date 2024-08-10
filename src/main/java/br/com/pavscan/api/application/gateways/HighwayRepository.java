package br.com.pavscan.api.application.gateways;

import br.com.pavscan.api.domain.entities.highway.Highway;
import br.com.pavscan.api.domain.entities.user.User;
import org.springframework.http.ResponseEntity;

public interface HighwayRepository {
    ResponseEntity<Highway> getHighwayById(Long Id);
    Highway createHighway(Highway highway);

    ResponseEntity<Highway> getHighwayByName(String name);

    ResponseEntity<Highway> updateHighway(Highway highway, Long id);

    ResponseEntity<Object> deleteHighway(Long id);
}
