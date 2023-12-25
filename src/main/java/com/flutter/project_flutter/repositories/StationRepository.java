package com.flutter.project_flutter.repositories;

import com.flutter.project_flutter.entites.Station;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StationRepository extends JpaRepository<Station, Integer> {
    Optional<Station> findByStationName(String stationName);

}
