package com.tpi.agencia.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter @NoArgsConstructor @ToString
@Entity
@Table(name = "Posiciones")
public class PosicionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_VEHICULO")
    private VehiculoEntity vehiculo;

    @Column(name = "FECHA_HORA")
    private Date fechaHora;

    private Double latitud;

    private Double longitud;

}
