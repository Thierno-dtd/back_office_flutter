package com.flutter.project_flutter.repositories;

import com.flutter.project_flutter.constants.AbonnementStaut;
import com.flutter.project_flutter.entites.Abonnement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AbonnementRepository extends JpaRepository<Abonnement, Integer> {
    List<Abonnement> findByClientIdAndDateDebutAfterAndDateFinBeforeAndStatut(int id, LocalDateTime d1, LocalDateTime d2,AbonnementStaut staut);

    List<Abonnement> findByClientId(int id);

    List<Abonnement> findByClientIdAndStatut(int id, AbonnementStaut statut);

    List<Abonnement> findByStatutAndDateFinBefore(AbonnementStaut active, LocalDateTime deadLineForExpiration);
}
