package com.flutter.project_flutter.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String pname;
    private String email;
    private String passwd;
    private BigDecimal solde;
    private Collection<String> roles;
    @OneToMany(mappedBy = "partener")
    private List<Station> stations;
    @OneToMany(mappedBy = "partenerTA")
    private List<TypeAbonnement> typeAbonnements;
    @OneToMany(mappedBy = "client")
    private List<Abonnement> abonnements;
}
