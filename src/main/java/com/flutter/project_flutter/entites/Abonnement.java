package com.flutter.project_flutter.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Abonnement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime date_debut;
    //private LocalDate date_fin;
    private float nbre_litre;
    private BigDecimal prix;
    private float nbre_litre_use;
    @ManyToOne
    @JoinColumn(name = "typesAbonnement_id")
    private TypeAbonnement typeAbonnement;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;
    @OneToMany(mappedBy = "abonnements")
    private List<DepenseEssence> depenseEessences;

}
