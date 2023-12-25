package com.flutter.project_flutter.repositories;

import com.flutter.project_flutter.entites.TypeAbonnement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypeAbonnementRepository extends JpaRepository<TypeAbonnement, Integer> {
    Optional<TypeAbonnement> findByLibelle(String libelle);
}
