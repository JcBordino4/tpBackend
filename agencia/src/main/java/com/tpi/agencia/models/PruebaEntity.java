package com.tpi.agencia.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter @NoArgsConstructor @ToString
@Entity
@Table(name = "Pruebas")
public class PruebaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_VEHICULO")
    private VehiculoEntity vehiculo;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_EMPLEADO")
    private EmpleadoEntity empleado;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_INTERESADO")
    private InteresadoEntity interesado;

    @Column(name = "FECHA_HORA_INICIO")
    private Date fechaHoraInicio;

    @Column(name = "FECHA_HORA_FIN")
    private Date fechaHoraFin;

    private String comentarios;


}
