package com.tpi.agencia.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter @Setter @NoArgsConstructor @ToString
@Entity
@Table(name = "Empleados")
public class EmpleadoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer legajo;

    private String nombre;

    private String apellido;

    @Column(name = "telefono_contacto")
    private Integer telefonoContacto;

    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL)
    private Set<PruebaEntity> pruebas = new HashSet<>();

}
