package com.flutter.project_flutter.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    @NotEmpty
    private String libelle;
    @NotNull
    @NotEmpty
    private  float nbre_litre;
    @NotNull
    @NotEmpty
    private BigDecimal prix;
    private String description;

    @ManyToOne
    @JoinColumn(name = "partener_id")
    private User partenerTA;
    @OneToMany(mappedBy = "typeAbonnement")
    private List<Abonnement> abonnements;
}
