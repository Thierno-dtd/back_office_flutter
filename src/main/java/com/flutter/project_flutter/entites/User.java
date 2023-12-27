package com.flutter.project_flutter.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private String pname;
    @NotNull
    @NotEmpty
    @Email
    private String email;
    @NotNull
    @NotEmpty
    private String passwd;
    @NotNull
    @NotEmpty
    private BigDecimal solde;
    @NotNull
    @NotEmpty
    private Collection<String> roles;
    @OneToMany(mappedBy = "partener")
    private List<Station> stations;
    @OneToMany(mappedBy = "partenerTA")
    private List<TypeAbonnement> typeAbonnements;
    @OneToMany(mappedBy = "client")
    private List<Abonnement> abonnements;
}
