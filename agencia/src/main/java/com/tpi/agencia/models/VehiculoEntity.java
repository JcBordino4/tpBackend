package com.tpi.agencia.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.Banner;

import java.util.Set;

@Getter @Setter @NoArgsConstructor @ToString
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

    @OneToMany(mappedBy = "", cascade = CascadeType.ALL)
    private Set<PosicionEntity> posiciones;
}
