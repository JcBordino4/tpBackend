package com.tpi.agencia.models;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.Banner;

import java.util.Objects;
import java.util.Set;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
@Entity
@Table(name = "Vehiculos")
public class VehiculoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String patente;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_MODELO")
    private ModeloEntity modelo;

    private Integer anio;

    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL)
    private Set<PruebaEntity> pruebas;

    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL)
    private Set<PosicionEntity> posiciones;

}
