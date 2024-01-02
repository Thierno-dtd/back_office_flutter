package com.flutter.project_flutter.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    @NotEmpty
    private LocalDateTime date_debut;
    @NotNull
    @NotEmpty
    private LocalDate date_fin;
    @NotNull
    @NotEmpty
    private float nbre_litre;
    @NotNull
    @NotEmpty
    private BigDecimal prix;
    @NotNull
    @NotEmpty
    private float nbre_litre_use;
    @ManyToOne
    @NotNull
    @NotEmpty
    @JoinColumn(name = "typesAbonnement_id")
    private TypeAbonnement typeAbonnement;
    @ManyToOne
    @NotNull
    @NotEmpty
    @JoinColumn(name = "client_id")
    private User client;
    @OneToMany(mappedBy = "abonnements")
    private List<DepenseEssence> depenseEessences;

}
