package com.flutter.project_flutter.repositories;

import com.flutter.project_flutter.entites.Abonnement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AbonnementRepository extends JpaRepository<Abonnement, Integer> {
    List<Abonnement> findByClientIdAndDateDebutAfterAndDateFinBefore(int id, LocalDateTime d1, LocalDateTime d2);

}
