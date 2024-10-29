package com.tpi.agencia.models;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter @Setter @NoArgsConstructor @ToString @AllArgsConstructor @EqualsAndHashCode
@Entity
@Table(name = "Interesados")
public class InteresadoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "TIPO_DOCUMENTO")
    private String tipoDocumento;

    private String documento;

    private String nombre;

    private String apellido;

    private Boolean restringido;

    @Column(name = "NRO_LICENCIA")
    private Integer nroLicencia;

    @Column(name = "FECHA_VENCIMIENTO_LICENCIA")
    private Date fechaVtoLicencia;

    @OneToMany(mappedBy = "interesado", cascade = CascadeType.ALL)
    private Set<PruebaEntity> pruebas = new HashSet<>();

}
