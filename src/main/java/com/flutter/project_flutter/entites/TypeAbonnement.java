package com.flutter.project_flutter.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class TypeAbonnement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String libelle;
    private  float nbre_litre;
    private BigDecimal prix;
    private String description;

    @ManyToOne
    @JoinColumn(name = "partener_id")
    private User partenerTA;
    @OneToMany(mappedBy = "typeAbonnement")
    private List<Abonnement> abonnements;
}
