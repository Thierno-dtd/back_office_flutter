package com.flutter.project_flutter.entites;

import com.flutter.project_flutter.constants.AbonnementStaut;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
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

    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private float nbre_litre;
    private float bonus;
    private BigDecimal prix;
    private float nbre_litre_use;
    @Column(name = "statut", nullable = false)
    @Enumerated(EnumType.STRING)
    private AbonnementStaut statut;
    @ManyToOne
    @JoinColumn(name = "typesAbonnement_id")
    private TypeAbonnement typeAbonnement;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;
    @OneToMany(mappedBy = "abonnements")
    private List<DepenseEssence> depenseEessences;

}
