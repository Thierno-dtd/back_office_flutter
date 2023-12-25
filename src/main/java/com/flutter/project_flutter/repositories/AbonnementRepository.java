package com.flutter.project_flutter.repositories;

import com.flutter.project_flutter.entites.Abonnement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbonnementRepository extends JpaRepository<Abonnement, Integer> {
}
